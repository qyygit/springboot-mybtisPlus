package com.qyy.copy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/4/12 10:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String name;
    private Integer age;
    private String height;
}
