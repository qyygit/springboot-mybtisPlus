package com.atguigu.boot.mybatisPlus;

import com.atguigu.boot.bean.User;
import com.atguigu.boot.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/5 16:22
 **/
@Slf4j
@RequestMapping("/plus")
@RestController
public class MybatisPlusMapperController {


    @Autowired
    private UserMapper userMapper;



    //根据id 更新
    @GetMapping(value = "/test11")
    public void test11(@RequestParam("name")String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name").eq("name",name);
        Integer integer = userMapper.selectCount(wrapper);
        System.out.println("根据条件统计数量" + integer);
    }



    //根据id 更新
    @PostMapping(value = "/test10")
    public void test10(@RequestBody User user) {
        int number = userMapper.updateById(user);
        System.out.println("根据id更新条数" + number);
    }


    //根据条件构造器wrapper进行更新
    @GetMapping(value = "/test09")
    public void test09(@RequestParam("name")String name) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,name);
        User user = new User();
        user.setAge(188);
        int number = userMapper.update(user, wrapper);
        System.out.println("更新条数" + number);
    }

    //根据 wrapper 条件，查询记录，将查询结果封装为一个Map，Map的key为结果的列，value为值
    @GetMapping(value = "/test08")
    public void test08(@RequestParam("name")String name) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","age").eq("name",name);
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }



   // 根据map中指定的列名和列值进行等值匹配 查找
    // 根据主键id进行查找
    @PostMapping(value = "/test07")
    public void test07(@RequestParam("map")HashMap<String, Object> map) {
        map.put("name","qyy");
        List<User> userList = userMapper.selectByMap(map);
        for (User user : userList) {
            System.out.println(user);
        }
    }


    // 根据主键id进行查找
    @PostMapping(value = "/test06")
    public void test06(@RequestParam("list") List<Integer> list) {
        List<User> userList = userMapper.selectBatchIds(list);
        for (User user : userList) {
            System.out.println(user);
        }
    }


    // 根据主键id进行查找
    @GetMapping(value = "/test05")
    public void test05(@RequestParam("id")Integer id) {
        User user = userMapper.selectById(id);
        System.out.println(user);
    }


    // 根据条件构造器wrapper进行删除
    @GetMapping(value = "/test04")
    public void test04(@RequestParam("name")String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,name);
        userMapper.delete(wrapper);
    }



    // 根据ID删除数据
    @GetMapping(value = "/test03/{id}")
    public void test03(@PathVariable("id") Long id) {
        userMapper.deleteById(id);
    }

   // 插入一条记录
    @PostMapping(value = "/test02")
    public void test02(@RequestParam String name) {
        User user = new User();
        user.setName(name);
      userMapper.insert(user);
    }

    /**
     * @Author: QYY
     * @Description: 查询数量匹配,校验
     * 如果两者不一致, 程序继续往下运行
     * 如果两者一致, 中断测试方法, 抛出异常信息 AssertionFailedError
     * @DateTime: 2022/1/6 16:13
     * @Params: []
     * @Return void
     */
    @GetMapping(value = "/test01")
    public void testSelect() {
        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(6,list.size());
        Assert.assertNotEquals(6,list.size());
        list.forEach(System.out::println);
    }


}
