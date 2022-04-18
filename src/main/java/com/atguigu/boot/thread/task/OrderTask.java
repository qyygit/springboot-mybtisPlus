package com.atguigu.boot.thread.task;

import com.atguigu.boot.thread.pojo.User;
import com.atguigu.boot.thread.utils.SynchroniseUtil;
import com.atguigu.boot.thread.vo.OrderVO;

import java.util.List;

public class OrderTask implements Runnable {
    private OrderVO orderVO;
    private List<User> users;
    private SynchroniseUtil<OrderVO> synchroniseUtil;
 
    public OrderTask(OrderVO orderVO, 
                     List<User> users, 
                     SynchroniseUtil<OrderVO> synchroniseUtil) {
        this.orderVO = orderVO;
        this.users = users;
        this.synchroniseUtil = synchroniseUtil;
    }
 
    @Override
    public void run() {
        //模拟从数据库里查数据
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (User user : users) {
            if (orderVO.getUserId().equals(user.getId())) {
                orderVO.setUserName(user.getUserName());
                break;
            }
        }
        synchroniseUtil.addResult(orderVO);
    }
}
