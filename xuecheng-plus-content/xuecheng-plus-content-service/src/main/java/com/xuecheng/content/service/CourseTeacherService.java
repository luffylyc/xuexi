package com.xuecheng.content.service;

import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

/**
 * @author cmy
 * @version 1.0
 * @description 教师信息管理接口
 * @date 2023/2/28 16:19
 */
public interface CourseTeacherService {
    /***
     * @description 师资信息查询接口
     * @param courseId 课程id
     * @return
    */
    List<CourseTeacher> getCourseTeacherList(Long courseId);

    /***
     * @description 课程信息修改接口
     * @param courseTeacher 修改信息
     * @return
    */
    CourseTeacher saveCourseTeacher(CourseTeacher courseTeacher);

    /***
     * @description 课程信息删除接口
     * @param courseId 课程id
     * @param teacherId 教师id
    */
    void deleteCourseTeacher(Long courseId, Long teacherId);
}
