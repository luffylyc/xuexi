package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Repository
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * @param courseId
     * @return com.xuecheng.content.model.dto.TeachplanDto
     * @description 查询某课程的课程计划，组成树型结构
     * @author Mr.M
     * @date 2022/9/9 11:10
     */
    public List<TeachplanDto> selectTreeNodes(long courseId);
}
