package com.atguigu.boot.thread.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/4/18 11:13
 **/
@Data
public class Order {

    private Long  id;
    private Long  userId;
    private LocalDateTime createTime;
}
