package com.atguigu.boot.feignclient.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 活动日志信息表
 * </p>
 *
 * @author lingo
 * @since 2021-11-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("web_log")
public class WebLog {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String createUser;
    private LocalDateTime createTime;
    /**
     * 根路径
     */
    private String basePath;

    /**
     * 花费时间
     */
    private Integer spendTime;

    private String uri;

    /**
     * 路径
     */
    private String url;

    /**
     * ip
     */
    private String ip;

    /**
     * 参数
     */
    private String parameter;

    /**
     * 返回结果
     */
    private String result;

    /**
     * 描述
     */
    private String description;



    /**
     * 活动
     */
    private String method;

    /**
     * 是否成功
     */
    private boolean flag;


}
