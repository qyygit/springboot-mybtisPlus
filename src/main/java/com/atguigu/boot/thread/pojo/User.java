package com.atguigu.boot.thread.pojo;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/4/18 11:12
 **/
@Data
public class User {
    private long id;
    private String userName;
    private String phone;
    private Integer age;
    private LocalDateTime birthday;
}
