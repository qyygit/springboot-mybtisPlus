package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CityMapper  extends BaseMapper<City> {
    
    @Select("select * from  city where  id=#{id}")
    public City getById(Long id);

//    @Insert("insert into city(name,state,country) VALUES(#{name},#{state},#{country})")
//    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void   saveCity(City city);
}
