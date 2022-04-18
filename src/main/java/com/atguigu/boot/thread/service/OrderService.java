package com.atguigu.boot.thread.service;


import com.atguigu.boot.thread.pojo.User;
import com.atguigu.boot.thread.vo.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> createDataOrder();

    List<User> createDataUser();
    List<OrderVO> singleThread();

    List<OrderVO> multiThread(List<OrderVO> orderVOList,List<User> users);
}
