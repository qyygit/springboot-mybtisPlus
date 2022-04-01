package com.atguigu.boot.bean;

import com.atguigu.boot.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName(value = "user2")
public class User2 extends Entity<String> {
 private String name;
 private Integer age;
 private String email;
 private Long managerId;

}
