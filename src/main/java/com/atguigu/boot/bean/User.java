package com.atguigu.boot.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.atguigu.boot.mybatisPlus.EncryptTypeHandler;
import com.atguigu.boot.mybatisPlus.ListTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "user",autoResultMap = true)
@Getter
@Setter
public class User   {

    /**
     * 所有属性都应该在数据库中
     */
    @TableField(exist = false)  //当前属性表中不存在
    private String userName;
    @TableField(exist = false)
    private String password;


    //以下是数据库字段
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

//    @TableField(condition = "%s LIKE CONCAT(#{%s},'%%')")
//    @TableField(condition = SqlCondition.LIKE)
//    @ExcelProperty()
    @ExcelProperty(value = "名称", index = 1)
    @TableField(typeHandler = ListTypeHandler.class)
    private List<String> name;
    @ExcelProperty(value = "年龄", index = 2)
    @TableField(condition = "%s &gt; #{%s}")
    private Integer age;
    @ExcelProperty(value = "邮箱", index = 3)
    @TableField(typeHandler = EncryptTypeHandler.class)
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
