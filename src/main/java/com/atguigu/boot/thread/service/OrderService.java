package com.atguigu.boot.thread.service;


import com.atguigu.boot.thread.pojo.User;
import com.atguigu.boot.thread.vo.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> createDataOrder();

    List<User> createDataUser();

    void singleThread() throws InterruptedException;

    List<OrderVO> multiThread(List<OrderVO> orderVOList,List<User> users);

    void singleThread2() throws InterruptedException, Exception;
}
