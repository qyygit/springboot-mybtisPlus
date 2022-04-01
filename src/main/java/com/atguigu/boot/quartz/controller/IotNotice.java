package com.atguigu.boot.quartz.controller;

import com.atguigu.boot.common.entity.Entity;
import com.atguigu.boot.mybatis.StringListTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 公告展示信息表
 * </p>
 *
 * @author lingo
 * @since 2021-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公告信息实体类")
@TableName(value = "iot_notice",autoResultMap = true)
public class IotNotice extends Entity<String> {


    @ApiModelProperty("仓库id")
    private String wareHouseId;
    /**
     * 公告类别
     */
    @NotEmpty(message = "公告标题不能为空")
    @ApiModelProperty("公告标题")
    private String noticeTitle;
    /**
     * 公告类别
     */
    @NotEmpty(message = "公告类别不能为空")
    @ApiModelProperty("公告类别")
    public Integer noticeType;

    /**
     * 内容
     */
    @ApiModelProperty("公告内容")
    private String content;

    /**
     * 展示时间
     */
    @ApiModelProperty("展示时间")
    private LocalDateTime showTime;
    /**
     * 展示时长  单位 分钟
     */
    @ApiModelProperty("展示时长")
    private Integer showDuration;
    /**
     * 播放模式
     * 1 循环一次
     * 0 无线循环
     */
    @ApiModelProperty("播放模式 1循环一次 0 无限循环")
    private Boolean showModel;

    /**
     * 是否展示
     * 0 不展示
     * 1 展示
     */
    @ApiModelProperty("是否展示")
    private Integer showFlag;


    @ApiModelProperty("展示次数")
    private Integer showCount;

    @ApiModelProperty("展示间隔")
    private Integer showSpace;



    @ApiModelProperty("缩略图")
    private String thumbnail;

    @ApiModelProperty("缩略图地址")
    private String thumbnailPath;

    @ApiModelProperty("缩略图地址")
    private Integer fileCount;


    @ApiModelProperty("播放大屏")
    @TableField(typeHandler = StringListTypeHandler.class)
    private List<String> simis;

    @ApiModelProperty("屏保 0否 1是")
    private boolean screenProtect ;

    @ApiModelProperty("文字公告背景")
    private String  background ;

}
