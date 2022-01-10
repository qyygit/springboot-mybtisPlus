package com.atguigu.boot.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 基础实体
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class Entity<T> extends SuperEntity<T> {

    public static final String UPDATE_TIME = "updateTime";
    public static final String UPDATE_USER = "updateUser";
    public static final String DELETED = "deleted";

    @ApiModelProperty(value = "最后修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;

    @ApiModelProperty(value = "最后修改人")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    protected String updateUser;


    @TableField(value = "deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleted;

    public Entity() {
    }

    public Entity(T id, LocalDateTime createTime, String createUser, LocalDateTime updateTime, String updateUser, Integer deleted) {
        super(id, createTime, createUser);
        this.updateTime = updateTime;
        this.updateUser = updateUser;
        this.deleted = deleted;
    }

}
