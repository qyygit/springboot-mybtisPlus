package com.atguigu.boot.bean;

import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@TableName("city")
public class City extends Model<City> {
    private Long id;
    @TableField(condition = SqlCondition.LIKE)
    private String name;
    private String state;
    private String country;

}
