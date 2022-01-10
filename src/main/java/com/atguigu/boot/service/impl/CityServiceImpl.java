package com.atguigu.boot.service.impl;


import com.atguigu.boot.bean.City;
import com.atguigu.boot.mapper.CityMapper;
import com.atguigu.boot.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Autowired
    CityMapper cityMapper;


    @Override
    public City getById(Long id) {
        return cityMapper.getById(id);
    }

    @Override
    public void saveCity(City city) {
        cityMapper.saveCity(city);
    }
}
