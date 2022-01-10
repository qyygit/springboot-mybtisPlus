package com.atguigu.boot.service.impl;

import com.atguigu.boot.bean.User;
import com.atguigu.boot.mapper.UserMapper;
import com.atguigu.boot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findBySql() {
        return userMapper.findBySql();
    }

    @Override
    public List<User> selectRaw() {
        return userMapper.selectRaw();
    }
}
