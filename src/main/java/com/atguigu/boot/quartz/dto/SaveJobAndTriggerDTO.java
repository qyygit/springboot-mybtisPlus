package com.atguigu.boot.quartz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.quartz.JobDataMap;

import java.io.Serializable;

@Data
@ApiModel(description = "增加定时任务实体类")
public class SaveJobAndTriggerDTO implements Serializable {


    private static final long serialVersionUID = -24506L;

    // 任务名称
    @ApiModelProperty(value = "任务名称",required = true)
    private String jobName;

    // 描述
    @ApiModelProperty(value = "描述",required = false)
    private String jobDescription;

    // 组
    @ApiModelProperty(value = "组名",required = true)
    private String jobGroupName;

    // 类名
    @ApiModelProperty(value = "类名",required = true)
    private String jobClassName;

    // 触发类
    @ApiModelProperty(value = "触发类",required = false)
    private String triggerName;

    // 触发组
    @ApiModelProperty(value = "触发组",required = true)
    private String triggerGroupName;

    // 时间表达式
    @ApiModelProperty(value = "时间表达式",required = true)
    private String cronExpression;

    @ApiModelProperty(value = "参数",required = false)
    private JobDataMap jobDataMap =new JobDataMap();

}