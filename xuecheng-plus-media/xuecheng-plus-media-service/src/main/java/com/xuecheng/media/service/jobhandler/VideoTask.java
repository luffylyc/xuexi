package com.xuecheng.media.service.jobhandler;

import com.xuecheng.base.utils.Mp4VideoUtil;
import com.xuecheng.media.model.po.MediaProcess;
import com.xuecheng.media.service.MediaFileProcessService;
import com.xuecheng.media.service.MediaFileService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/* * @author cmy
 * @version 1.0
 * @description 视频处理任务
 * @date 2023/3/5 13:41*/


//使用需要打开此文件，xxl依赖，config包下xxl文件，并运行java -jar xxl-job-admin-2.3.1.jar
@Slf4j
@Component
public class VideoTask {

    @Autowired
    MediaFileService mediaFileService;
    @Autowired
    MediaFileProcessService mediaFileProcessService;


    @Value("${videoprocess.ffmpegpath}")
    String ffmpegpath;

    @XxlJob("videoJobHander")
    public void videoJobHander() throws Exception {

        // 分片参数 从0开始
        int shardIndex = XxlJobHelper.getShardIndex();
        //分片总数
        int shardTotal = XxlJobHelper.getShardTotal();
        List<MediaProcess> mediaProcessList = null;
        int size = 0;

        //从mediaProcess数据库中取出需要处理的信息
        try {
            //取出2条记录，一次处理视频数量不要超过cpu核心数，count为cpu核心数
            mediaProcessList = mediaFileProcessService.getMediaProcessList(shardIndex, shardTotal, 2);
            size = mediaProcessList.size();
            log.debug("取出待处理视频任务{}条", size);
            if (size < 0) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        //启动size个线程的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(size);
        //计数器
        CountDownLatch countDownLatch = new CountDownLatch(size);

        //将处理任务加入线程池
        mediaProcessList.forEach(mediaProcess -> {
            threadPool.execute(() -> {
                //需要处理文件所在桶
                String bucket = mediaProcess.getBucket();
                //需要处理文件存储路径
                String filePath = mediaProcess.getFilePath();
                //需要处理文件的md5值
                String fileId = mediaProcess.getFileId();
                //需要处理文件名称
                String filename = mediaProcess.getFilename();
                //需要处理文件状态
                String status = mediaProcess.getStatus();

                //如果状态为2，证明已经经过处理
                if ("2".equals(status)) {
                    log.debug("视频已经处理成功，不再处理,文件:{},路径:{}", filename, filePath);
                    countDownLatch.countDown();
                    return;
                }

                //创建本地接受源文件和处理后文件的本地存储路径
                //将要处理的文件下载到服务器
                File originalFile = null;
                //处理结束的视频文件
                File mp4File = null;
                try {
                    //源文件下载到本地的存储路径
                    originalFile = File.createTempFile("original", null);
                    //转码后文件在本地的存储路径
                    mp4File = File.createTempFile("mp4", ".mp4");
                } catch (IOException e) {
                    log.error("处理视频前创建临时文件失败");
                    countDownLatch.countDown();
                    return;
                }

                //从minio下载需要处理的文件
                try {
                    //下载文件
                    mediaFileService.downloadFileFromMinIO(originalFile, mediaProcess.getBucket(), mediaProcess.getFilePath());
                } catch (Exception e) {
                    log.error("处理视频前下载原始文件:{},出错:{}", mediaProcess.getFilePath(), e.getMessage());
                    countDownLatch.countDown();
                    return;
                }

                //在本地进行文件转码处理
                String result = null;
                try {
                    String mp4_name = fileId + ".mp4";
                    //开始处理视频
                    Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpegpath, originalFile.getAbsolutePath(), mp4_name, mp4File.getAbsolutePath());
                    //开始视频转换，成功将返回success
                    result = videoUtil.generateMp4();
                } catch (Exception e) {
                    log.error("处理视频文件:{},出错:{}", mediaProcess.getFilePath(), e.getMessage());
                    countDownLatch.countDown();
                    return;
                }

                //转码失败在数据库mediaFileProcess表中更新错误信息
                if (!result.equals("success")) {
                    //记录错误信息
                    log.error("处理视频失败,视频地址:{},错误信息:{}", bucket + filePath, result);
                    mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "3", fileId, null, result);
                    countDownLatch.countDown();
                    return;
                }

                //处理成功，将mp4上传至minio
                //文件路径
                String objectName = null;
                try {
                    objectName = getFilePath(fileId, ".mp4");
                    mediaFileService.addMediaFilesToMinIO(mp4File.getAbsolutePath(), bucket, objectName);
                } catch (Exception e) {
                    log.error("上传视频失败,视频地址:{},错误信息:{}", bucket + objectName, e.getMessage());
                    countDownLatch.countDown();
                    return;
                }

                //转码成功在数据库mediaFileProcess表中更新信息
                try {
                    //访问url
                    String url = "/" + bucket + "/" + objectName;
                    //将url存储至数据，并更新状态为成功，并将待处理视频记录删除存入历史
                    mediaFileProcessService.saveProcessFinishStatus(mediaProcess.getId(), "2", fileId, url, result);
                } catch (Exception e) {
                    log.error("视频信息入库失败,视频地址:{},错误信息:{}", bucket + objectName, e.getMessage());
                }
                countDownLatch.countDown();
            });
        });
        //等待,给一个充裕的超时时间,防止无限等待，到达超时时间还没有处理完成则结束任务
        countDownLatch.await(30, TimeUnit.MINUTES);
    }

    private String getFilePath(String fileMd5, String fileExt) {
        return fileMd5.substring(0, 1) + "/" + fileMd5.substring(1, 2) + "/" + fileMd5 + "/" + fileMd5 + fileExt;
    }

}

