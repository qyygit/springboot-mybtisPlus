package com.atguigu.boot.dog.entity;

import com.atguigu.boot.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author qyy
 * @since 2022-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("dog")
public class Dog extends Entity<Long> {




    private String name;

    private Long money;


}
