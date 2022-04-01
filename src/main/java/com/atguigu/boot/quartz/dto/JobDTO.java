package com.atguigu.boot.quartz.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname JobDTO
 * @Description
 * @Date 2021/10/12 10:26
 * @Created by guolin
 */
@Data
@ApiModel("定时任务参数")
public class JobDTO {


    @ApiModelProperty("任务名称")
    private String Jname;
    @ApiModelProperty("任务组")
    private String Jgroup;

    /**
     * 展示时间
     */
    @ApiModelProperty("展示时间")
    private LocalDateTime showTime;
}