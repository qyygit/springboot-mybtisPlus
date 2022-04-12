package com.atguigu.boot.dog.controller;


import com.atguigu.boot.base.BaseController;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.dog.Dto.DogDto;
import com.atguigu.boot.dog.entity.Dog;
import com.atguigu.boot.dog.service.DogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qyy
 * @since 2022-03-01
 */
@RestController
@RequestMapping("/dog")
@Api(tags = "dog管理")
@Slf4j
public class DogController extends BaseController {

    @Autowired
    private DogService dogService;

    @ApiOperation(value = "新增宠物狗")
    @PostMapping(value = "/saveDog")
    public R saveDog(@RequestBody DogDto dogDto){
        log.info("新增宠物狗操作 dogDto : {}" , dogDto);

        Dog dog = dozerUtils.map(dogDto, Dog.class);
        dogService.save(dog);
        return R.success();
    }



//    @GetMapping(value = "/test/dozer")
//    public R test() {
//        Dog dog = new Dog();
//        dog.setName("哈哈");
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        for (int i = 0; i < 10000; i++) {
//          dozerUtils.map(dog, DogDto.class);
//        }
//        stopWatch.stop();
//
//        System.out.println("dozer耗时" + stopWatch.getTotalTimeMillis());
//
//        return R.success();
//    }
//
//    @GetMapping(value = "/test/beanCopie")
//    public R test2() {
//        Dog dog = new Dog();
//        dog.setName("哈哈");
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        for (int i = 0; i < 10000; i++) {
//            BeanUtils.copy(dog, DogDto.class);
//        }
//        stopWatch.stop();
//
//        System.out.println("beanCopie" + stopWatch.getTotalTimeMillis());
//
//        return R.success();
//    }

    public static void main(String[] args) {
        //创建并启动StopWatch
        StopWatch stopwatch = StopWatch.createStarted();
        //业务逻辑......
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
        stopwatch.stop();
        //分钟,不足59秒,返回0
        System.out.println("分钟单位" +  " :" + stopwatch.getTime(TimeUnit.MINUTES));
        //毫秒单位
        System.out.println("毫秒单位" + " :" + stopwatch.getTime(TimeUnit.MILLISECONDS));
        //微妙单位
        System.out.println("微妙单位" + " :" +stopwatch.getTime(TimeUnit.MICROSECONDS));
    }

}

