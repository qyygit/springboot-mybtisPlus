package com.atguigu.boot.quartz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.quartz.JobDataMap;

import java.io.Serializable;

@Data
@ApiModel(description = "修改定时任务实体类")
public class UpdateJobAndTriggerDTO implements Serializable {




    // 触发类
    @ApiModelProperty(value = "触发类",required = true)
    private String triggerName;

    // 触发组
    @ApiModelProperty(value = "触发组",required = true)
    private String triggerGroupName;

    // 时间表达式
    @ApiModelProperty(value = "时间表达式",required = true)
    private String cronExpression;

    @ApiModelProperty(value = "参数",required = false)
    private JobDataMap jobDataMap;

}