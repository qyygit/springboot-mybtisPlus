package com.atguigu.boot.service.impl;

import com.atguigu.boot.bean.Account;
import com.atguigu.boot.mapper.AccountMapper;
import com.atguigu.boot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    public Account getAccountByiD(Long id){
     return    accountMapper.getAccount(id);
    }
}
