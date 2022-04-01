package com.atguigu.boot.dog.Dto;

import com.atguigu.boot.common.entity.Entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

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
public class DogDto extends Entity<String> {


    @NotBlank(message = "宠物名不能为空")
    private String name;

    @NotBlank(message = "宠物价格不能为空")
    private Long money;


}
