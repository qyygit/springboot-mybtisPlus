package com.atguigu.boot.dog.controller;


import com.atguigu.boot.base.BaseController;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.dog.Dto.DogDto;
import com.atguigu.boot.dog.entity.Dog;
import com.atguigu.boot.dog.service.DogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

}

