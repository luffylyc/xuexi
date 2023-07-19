package com.xuecheng.content.model.dto;

import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author cmy
 * @version 1.0
 * @description 课程计划树的输出类
 * @date 2023/2/27 22:06
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {
    //课程计划关联的媒资信息
    TeachplanMedia teachplanMedia;

    //课程计划子节点
    List<TeachplanDto> teachPlanTreeNodes;
}
