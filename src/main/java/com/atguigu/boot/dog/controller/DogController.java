package com.atguigu.boot.dog.controller;


import com.atguigu.boot.base.BaseController;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.dog.Dto.DogDto;
import com.atguigu.boot.dog.entity.Dog;
import com.atguigu.boot.dog.service.DogService;
import com.atguigu.boot.utils.bean.BeanUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping(value = "/test/dozer")
    public R test() {
        Dog dog = new Dog();
        dog.setName("哈哈");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 10000; i++) {
          dozerUtils.map(dog, DogDto.class);
        }
        stopWatch.stop();

        System.out.println("dozer耗时" + stopWatch.getTotalTimeMillis());

        return R.success();
    }

    @GetMapping(value = "/test/beanCopie")
    public R test2() {
        Dog dog = new Dog();
        dog.setName("哈哈");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for (int i = 0; i < 10000; i++) {
            BeanUtils.copy(dog, DogDto.class);
        }
        stopWatch.stop();

        System.out.println("beanCopie" + stopWatch.getTotalTimeMillis());

        return R.success();
    }


}

