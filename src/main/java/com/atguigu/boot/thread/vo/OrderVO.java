package com.atguigu.boot.thread.vo;

import com.atguigu.boot.thread.pojo.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/4/18 11:18
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderVO extends Order {


    private String userName;
}
