package com.atguigu.boot.service;

import com.atguigu.boot.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {


    List<User> findBySql();

    List<User> selectRaw();
}
