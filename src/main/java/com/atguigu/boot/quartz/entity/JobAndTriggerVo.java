package com.atguigu.boot.quartz.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "定时任务实体类")
public class JobAndTriggerVo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
    // 上次执行时间
    @ApiModelProperty(value = "上次执行时间",required = false)
    private String prevFireTime;
    // 下次执行时间
    @ApiModelProperty(value = "下次执行时间",required = false)
    private String nextFireTime;
    // 时间表达式
    @ApiModelProperty(value = "时间表达式",required = true)
    private String cronExpression;
    // 状态
    /**
     * WAITING:等待
     * PAUSED:暂停
     * ACQUIRED:正常执行
     * BLOCKED：阻塞
     * ERROR：错误
     */
    @ApiModelProperty(value = "状态",required = false)
    private String triggerState;
}