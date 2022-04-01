package com.atguigu.boot.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
@TableName("user")
@Getter
@Setter
public class UserEasy implements Serializable {


    @ExcelProperty(value = "名称", index = 0)
    private String name;
    @ExcelProperty(value = "年龄", index = 1)
    @TableField(condition = "%s &gt; #{%s}")
    private Integer age;
    @ExcelProperty(value = "邮箱", index = 2)
    private String email;

}
