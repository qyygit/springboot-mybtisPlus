package com.atguigu.boot.dog.service.impl;

import com.atguigu.boot.dog.entity.Dog;
import com.atguigu.boot.dog.mapper.DogMapper;
import com.atguigu.boot.dog.service.DogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qyy
 * @since 2022-03-01
 */
@Service
public class DogServiceImpl extends ServiceImpl<DogMapper, Dog> implements DogService {

}
