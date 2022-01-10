package com.atguigu.boot.service;

import com.atguigu.boot.bean.City;
import com.baomidou.mybatisplus.extension.service.IService;


public interface CityService extends IService<City> {

    public City getById(Long id);

    public void saveCity(City city);

    }

