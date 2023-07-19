package com.xuecheng.content.service.impl;

import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author cmy
 * @version 1.0
 * @description 课程分类接口实现
 * @date 2023/2/25 20:34
 */
@Slf4j
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    public List<CourseCategoryTreeDto> queryTreeNodes(String id) {
        //查询数据库得到的课程分类
        List<CourseCategoryTreeDto> courseCategoryTreeDtos = courseCategoryMapper.selectTreeNodes(id);
        //最终返回的列表
        List<CourseCategoryTreeDto> categoryTreeDtos = new ArrayList<>();
        HashMap<String, CourseCategoryTreeDto> mapTemp = new HashMap<>();

/*        //stream流遍历
        courseCategoryTreeDtos.stream().forEach(item->{
            mapTemp.put(item.getId(),item);
            //只将根节点的下级节点放入list
            if(item.getParentid().equals(id)){
                categoryTreeDtos.add(item);
            }
            CourseCategoryTreeDto courseCategoryTreeDto = mapTemp.get(item.getParentid());
            if(courseCategoryTreeDto!=null){
                if(courseCategoryTreeDto.getChildrenTreeNodes() ==null){
                    courseCategoryTreeDto.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                //向节点的下级节点list加入节点
                courseCategoryTreeDto.getChildrenTreeNodes().add(item);
            }

        });*/
        for (CourseCategoryTreeDto courseCategoryTreeDto : courseCategoryTreeDtos) {
            mapTemp.put(courseCategoryTreeDto.getId(),courseCategoryTreeDto);
            if(courseCategoryTreeDto.getParentid().equals(id)){
                categoryTreeDtos.add(courseCategoryTreeDto);
            }
            CourseCategoryTreeDto courseCategoryTreeDto1 = mapTemp.get(courseCategoryTreeDto.getParentid());
            if(courseCategoryTreeDto1 != null){
                if(courseCategoryTreeDto1.getChildrenTreeNodes() == null){
                    courseCategoryTreeDto1.setChildrenTreeNodes(new ArrayList<CourseCategoryTreeDto>());
                }
                courseCategoryTreeDto1.getChildrenTreeNodes().add(courseCategoryTreeDto);
            }
        }

        return categoryTreeDtos;
    }

}
