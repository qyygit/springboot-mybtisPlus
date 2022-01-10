package com.atguigu.boot.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("city")
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;

}
