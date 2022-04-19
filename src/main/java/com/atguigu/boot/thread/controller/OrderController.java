package com.atguigu.boot.thread.controller;


import com.atguigu.boot.thread.pojo.User;
import com.atguigu.boot.thread.service.OrderService;
import com.atguigu.boot.thread.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderController {

    final OrderService orderService;

//    private List<OrderVO> orderVOS = new ArrayList<>();
//    private List<User> users = new ArrayList<>();

    //单例线程
    @GetMapping("/getSingleThread")
    public void getSingleThread() throws Exception {
        long startTime = System.currentTimeMillis();


         orderService.singleThread();
         orderService.singleThread2();

//        System.out.println(orderVOS.get(0));
        long endTime = System.currentTimeMillis();

        System.out.println("执行时间：" + (endTime - startTime) + " ms");

    }



    //多线程
    @GetMapping("/getMultiThread")
    public List<OrderVO> getOrderDetails() throws Exception {
        long startTime = System.currentTimeMillis();

        //模拟数据库数据
        List<OrderVO> orderVOList = orderService.createDataOrder();
        List<User> users = orderService.createDataUser();

        System.out.println("orderVOList"  + orderVOList.size());
        System.out.println("users"  + users.size());

        List<OrderVO> orderVOS = orderService.multiThread(orderVOList, users);
        long endTime = System.currentTimeMillis();
        System.out.println("orderVOS"  + orderVOS.size());

        System.out.println("执行时间：" + (endTime - startTime) + " ms");
        return orderVOList;
    }





    //初始化时就创建好数据。模拟数据库已经存在的数据
//    @PostConstruct
//    public void createData() {
//        long dataCount = 500;
//
//        // 创建订单数据。模拟已经插入到数据库的订单
//        for (long i = 0; i < dataCount; i++) {
//            OrderVO orderVO = new OrderVO();
//            orderVO.setId(i + 1);
//            orderVO.setUserId(i + 1);
//            //防止电脑太快，导致都是同一个时间，所以加一个数
//            orderVO.setCreateTime(LocalDateTime.now().plusSeconds(i));
//            orderVOS.add(orderVO);
//        }
//
//        // 创建用户数据。模拟已经插入到数据库的用户
//        for (long i = 0; i < dataCount; i++) {
//            User user = new User();
//            user.setId(i + 1);
//            user.setUserName("用户名" + (i + 1));
//            users.add(user);
//        }
//        orderVOS = orderVOS.stream()
//                .sorted(Comparator.comparing(OrderVO::getCreateTime).reversed())
//                .collect(Collectors.toList());
//    }


//    private List<OrderVO> multiThread(List<OrderVO> orders) throws Exception{
//        ExecutorService executor = ThreadPoolExecutors.getSingletonExecutor();
//        SynchroniseUtil<OrderVO> synchroniseUtil = new SynchroniseUtil<>(orders.size());
//        System.out.println("任务个数：" + orders.size());
//
//        for (OrderVO order : orders) {
//            OrderTask orderTask = new OrderTask(order, users, synchroniseUtil);
//            executor.execute(orderTask);
//        }
//
//        List<OrderVO> list = null;
//        try {
//            list = synchroniseUtil.get(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        if (list != null) {
//            list = list.stream()
//                    .sorted(Comparator.comparing(OrderVO::getCreateTime).reversed())
//                    .collect(Collectors.toList());
//        }
//
//        return list;
//    }



    //单例线程
//    private List<OrderVO> singleThread(List<OrderVO> orders) {
//        List<OrderVO> result = new ArrayList<>(orders);
//        for (OrderVO orderVO : result) {
//            //模拟从数据库里查数据
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (User user : users) {
//                if (orderVO.getUserId().equals(user.getId())) {
//                    orderVO.setUserName(user.getUserName());
//                    break;
//                }
//            }
//        }
//        return result;
//    }
}
