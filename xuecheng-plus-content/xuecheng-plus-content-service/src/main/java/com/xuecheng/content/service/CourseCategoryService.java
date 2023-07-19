package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.CourseCategoryTreeDto;

import java.util.List;

/**
 * @author cmy
 * @version 1.0
 * @description 课程分类相关service
 * @date 2023/2/25 20:31
 */
public interface CourseCategoryService {
    /***
     * @description 课程分类查询
     * @param id 根节点id
     * @return 根节点下方所有子节点
    */
    public List<CourseCategoryTreeDto> queryTreeNodes(String id);
}
