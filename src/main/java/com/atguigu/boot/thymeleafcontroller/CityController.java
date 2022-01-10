package com.atguigu.boot.thymeleafcontroller;

import com.atguigu.boot.bean.City;
import com.atguigu.boot.mapper.CityMapper;
import com.atguigu.boot.service.CityService;
import com.atguigu.boot.service.impl.CityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class CityController {

    @Autowired
    CityService cityService;


    @Autowired
    CityServiceImpl cityServiceImpl;

    @Autowired
    CityMapper cityMapper;

    @RequestMapping("/cityBatch")
    public void saveBacth(){
        List<City> list = new ArrayList<>();

            City city = new City();
             city.setId(100L);
            city.setName("111");
            city.setState("111");
            city.setCountry("111111");
            list.add(city);
        City city2 = new City();
        city2.setId(1001L);
        city2.setName("2");
        city2.setState("2");
        city2.setCountry("2");
        list.add(city2);

        cityServiceImpl.saveBatch(list);

    }
}
