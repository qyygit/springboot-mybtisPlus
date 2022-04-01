package com.atguigu.boot.bean;

import com.atguigu.boot.common.entity.Entity;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  Person extends Entity<String> {
	private String name;  // 姓名
	private int salary; // 薪资
	private int age; // 年龄
	private String sex; //性别
	private String area;  // 地区


	// 省略了get和set，请自行添加

}
