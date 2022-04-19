package com.atguigu.boot.thread.service.impl;


import com.atguigu.boot.thread.pojo.User;
import com.atguigu.boot.thread.service.OrderService;
import com.atguigu.boot.thread.task.OrderTask;
import com.atguigu.boot.thread.utils.SynchroniseUtil;
import com.atguigu.boot.thread.utils.ThreadPoolExecutors;
import com.atguigu.boot.thread.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/4/18 11:43
 **/
@Service
public class OrderServiceImpl implements OrderService {

//    private List<OrderVO> orderVOS = new ArrayList<>();
//    private List<User> users = new ArrayList<>();

    private   long dataCount = 10000;

    @Override
    public List<OrderVO> createDataOrder() {

        List<OrderVO> orderVOS = new ArrayList<>();
//
        // 创建订单数据。模拟已经插入到数据库的订单
        for (long i = 0; i < dataCount; i++) {
            OrderVO orderVO = new OrderVO();
            orderVO.setId(i + 1);
            orderVO.setUserId(i + 1);
            //防止电脑太快，导致都是同一个时间，所以加一个数
            orderVO.setCreateTime(LocalDateTime.now().plusSeconds(i));
            orderVOS.add(orderVO);
        }


        orderVOS = orderVOS.stream()
                .sorted(Comparator.comparing(OrderVO::getCreateTime).reversed())
                .collect(Collectors.toList());

        return orderVOS;
    }


    @Override
    public List<User> createDataUser() {

        List<User> users = new ArrayList<>();


        // 创建用户数据。模拟已经插入到数据库的用户
        for (long i = 0; i < dataCount; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setUserName("用户名" + (i + 1));
            users.add(user);
        }
        return users;
    }

    @Override
//    @Async
    public void singleThread() throws InterruptedException {

        Thread.sleep(1000);
        System.out.println("singleThread 方法一");
        //模拟数据
        List<OrderVO> orderVOS = createDataOrderNew();
        //模拟数据
        List<User> users = createDataUserNew();
        for (OrderVO orderVO : orderVOS) {
            try {
//                Thread.sleep(10);
                for (User user : users) {
                    if (orderVO.getUserId().equals(user.getId())) {
                        orderVO.setUserName(user.getUserName());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(users.size());

    }
//    @Async
    public void singleThread2() throws Exception {

        System.out.println("singleThread2 方法二");
        //模拟数据
        List<OrderVO> orderVOS = createDataOrderNew();
        //模拟数据
        List<User> users = createDataUserNew();
        for (OrderVO orderVO : orderVOS) {
            try {
//                Thread.sleep(10);
                for (User user : users) {
                    if (orderVO.getUserId().equals(user.getId())) {
                        orderVO.setUserName(user.getUserName());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(users.size());

    }

    @Override
    public List<OrderVO> multiThread(List<OrderVO> orderVOList, List<User> users) {
        ExecutorService executor = ThreadPoolExecutors.getSingletonExecutor();
        SynchroniseUtil<OrderVO> synchroniseUtil = new SynchroniseUtil<>(orderVOList.size());
        System.out.println("任务个数：" + orderVOList.size());

        for (OrderVO order : orderVOList) {
            OrderTask orderTask = new OrderTask(order, users, synchroniseUtil);
            executor.execute(orderTask);
        }

        List<OrderVO> list = null;
        try {
            list = synchroniseUtil.get(20, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null) {
            list = list.stream()
                    .sorted(Comparator.comparing(OrderVO::getCreateTime).reversed())
                    .collect(Collectors.toList());
        }

        return list;
    }

    public  List<OrderVO> createDataOrderNew() {

        List<OrderVO> orderVOS = new ArrayList<>();
//
        // 创建订单数据。模拟已经插入到数据库的订单
        for (long i = 0; i < dataCount; i++) {
            OrderVO orderVO = new OrderVO();
            orderVO.setId(i + 1);
            orderVO.setUserId(i + 1);
            //防止电脑太快，导致都是同一个时间，所以加一个数
            orderVO.setCreateTime(LocalDateTime.now().plusSeconds(i));
            orderVOS.add(orderVO);
        }


        orderVOS = orderVOS.stream()
                .sorted(Comparator.comparing(OrderVO::getCreateTime).reversed())
                .collect(Collectors.toList());

        return orderVOS;
    }


    public  List<User> createDataUserNew() {

        List<User> users = new ArrayList<>();


        // 创建用户数据。模拟已经插入到数据库的用户
        for (long i = 0; i < dataCount; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setUserName("用户名" + (i + 1));
            users.add(user);
        }
        return users;
    }
}
