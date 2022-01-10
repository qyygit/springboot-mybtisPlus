package com.atguigu.boot.mapper;

import com.atguigu.boot.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface UserMapper  extends BaseMapper<User> {
    @Update("truncate table user")
    void updateDelete();

    @Select("select *from user")
    List<User> findBySql();

    List<User> selectRaw();
}
