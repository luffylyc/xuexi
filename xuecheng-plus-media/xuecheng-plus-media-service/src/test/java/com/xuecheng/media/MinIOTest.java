package com.xuecheng.media;

import io.minio.*;
import io.minio.errors.MinioException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * @author cmy
 * @version 1.0
 * @description minio功能测试
 * @date 2023/3/1 15:08
 */
public class MinIOTest {
    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://39.105.221.68:9000")
                    .credentials("minioadmin", "minioadmin")
                    .build();

    @Test
    public void upload() {
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")
                            .object("cmy.png")//同一个桶内对象名不能重复
                            .filename("E:\\照片\\cmy.png")
                            .build());
            System.out.println("上传成功");

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("上传失败");
        }
    }

    @Test
    //指定桶内的子目录
    public void upload2() {
        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")
                            .object("test/cmy.png")//上传时指定子目录为/test
                            .filename("E:\\照片\\cmy.png")
                            .build());
            System.out.println("上传成功");

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("上传失败");
        }
    }

    @Test
    //删除文件
    public void delete() {
        try {
            RemoveObjectArgs build = RemoveObjectArgs.builder()
                    .bucket("testbucket")
                    .object("test/cmy.png")
                    .build();
            minioClient.removeObject(build);
            System.out.println("删除成功");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("删除失败");
        }
    }

    @Test
    //查询文件
    public void getFile() {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket("testbucket").object("cmy.png").build();
            FilterInputStream inputStream = minioClient.getObject(getObjectArgs);
            FileOutputStream outputStream = new FileOutputStream(new File("E:\\照片\\111.png"));
            if(inputStream != null) {
                IOUtils.copy(inputStream, outputStream);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}

/*    //上传文件
    public static void upload()throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found =minioClient.bucketExists(BucketExistsArgs.builder().bucket("testbucket").build());
            //检查testbucket桶是否创建，没有创建自动创建
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("testbucket").build());
            } else {
                System.out.println("Bucket 'testbucket' already exists.");
            }
            //上传1.mp4
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")
                            .object("cmy.png")//同一个桶内对象名不能重复
                            .filename("E:\\照片\\cmy.png")
                            .build());
            //上传1.avi,上传到avi子目录
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("testbucket")
                            .object("avi/1.avi")
                            .filename("D:\\develop\\upload\\1.avi")
                            .build());
            System.out.println("上传成功");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }
    public static void main(String[] args)throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        upload();
    }*/






