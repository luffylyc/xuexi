package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;

/**
 * @author cmy
 * @version 1.0
 * @description 课程管理service接口
 * @date 2023/2/25 14:57
 */
public interface CourseBaseInfoService  {

    /**
     * @description 课程信息管理业务查询接口
     * @param pageParams 分页参数
     * @param queryCourseParamsDto 查询条件
     * @return 课程列表信息
     * @author cmy
     * @date 2022/9/6 21:44
     */
    PageResult<CourseBase> queryCourseBaseList(Long companyID,PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    /**
     * @description 添加课程基本信息
     * @param companyId  教学机构id
     * @param addCourseDto  新增课程基本信息
     * @return 课程基本信息和营销信息
     * @author Mr.M
     * @date 2022/9/7 17:51
     */
    CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

    /***
     * @description 根据课程id查询课程基本信息，包括基本信息和营销信息
     * @param courseId 课程id
     * @return 课程基本信息和营销信息
    */
    CourseBaseInfoDto getCourseBaseInfo(long courseId);

    /**
     * @description 修改课程信息
     * @param companyId  机构id 本机构只能修改本机构的课程
     * @param dto  课程信息
     * @return 修改后的课程基本信息和营销信息
     */
    CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);

    /***
     * @description 删除课程信息接口
     * @param companyId 机构id 本机构只能修改本机构的课程
     * @param courseId 课程id
    */
    void delectCourse(Long companyId, Long courseId);





}
