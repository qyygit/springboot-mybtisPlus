package com.atguigu.boot.bean;

import com.atguigu.boot.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("user")
@Getter
@Setter
public class User extends Entity<Long> {

    /**
     * 所有属性都应该在数据库中
     */
    @TableField(exist = false)  //当前属性表中不存在
    private String userName;
    @TableField(exist = false)
    private String password;


    //以下是数据库字段
//    @TableId(value = "id",type = IdType.AUTO)
//    private Long id;

    @TableField(condition = "%s LIKE CONCAT(#{%s},'%%')")
    private String name;
    private Integer age;
    private String email;

//    @ApiModelProperty(value = "是否删除(1:已删除; 0：未删除)")
//    @TableLogic(value = "0", delval = "1")
//    @TableField(fill = FieldFill.INSERT)

    // 字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmt_create;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmt_modified;



}
