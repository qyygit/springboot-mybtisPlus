package com.atguigu.boot.mybatisPlus;

import com.alibaba.excel.EasyExcel;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.bean.User2;
import com.atguigu.boot.bean.UserEasy;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.dozer.DozerUtils;
import com.atguigu.boot.easyexcel.DeviceDataListener;
import com.atguigu.boot.easyexcel.ExcelUtils;
import com.atguigu.boot.mapper.User2Mapper;
import com.atguigu.boot.mapper.UserMapper;
import com.atguigu.boot.service.UserService;
import com.atguigu.boot.utils.IpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/5 16:22
 **/
@Slf4j
@RequestMapping("/plus")
@RestController
@Api(tags = "mybatis Plus")
public class MybatisPlusMapperController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User2Mapper user2Mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerUtils dozerUtils;


    @PostMapping(value = "/saveuser")
    @ApiOperation(value = "测试日志")
    public void saveuser(@RequestBody User user) {

        userService.save(user);
    }

    String PATH_URL = "D:\\Qyytest\\mybatisPlus\\springboot\\src\\main\\resources\\";





    // easyexcel上传文件
    @PostMapping("/upload")
    @ResponseBody
    public R upload(MultipartFile file, HttpServletRequest request) throws IOException {
//        UserEasy userEasy = new UserEasy();
//        userEasy.setName("测试");
//        User user = new User();
//
//        User mapUser = dozerUtils.map(userEasy, User.class);
//        userService.save(mapUser);

        log.info("设备导入文件{}", file.getOriginalFilename());
        EasyExcel.read(file.getInputStream(), UserEasy.class, new DeviceDataListener(userService, dozerUtils)).sheet().doRead();
        return R.success();
    }


    // Ip校验工具
    @GetMapping(value = "/ipUntil")
    public void ipUntil(@RequestParam("ip") String ip) {

        boolean bl = IpUtil.isIP(ip);

        System.out.println(bl);


    }


    // 文件导出 到 web端
    @GetMapping(value = "/uploadWeb")
    public void testIf2(HttpServletResponse response) throws Exception {
        List<User> list = userService.list(null);
        List<UserEasy> userEasyList = dozerUtils.mapList(list, UserEasy.class);
        ExcelUtils.export2Web(response, "EASY", "EASY", UserEasy.class, userEasyList);
    }


    // 文件导出 到 指定路径下
    @GetMapping(value = "/uploadPath")
    public void testIf() {
        List<User> list = userService.list(Wrappers.<User>lambdaQuery().eq(User::getName, "qyy"));
        List<UserEasy> userEasyList = dozerUtils.mapList(list, UserEasy.class);
        ExcelUtils.export2File(PATH_URL, "EASY", "EASY", UserEasy.class, userEasyList);
    }

    @ApiOperation(value = "初始化用户信息")
    @GetMapping(value = "/findAll")
    public R findAll() {

        QueryWrapper<User2> wrapper = new QueryWrapper<>();
        wrapper.select("name").groupBy("name");
        List<User2> user2s = user2Mapper.selectList(wrapper);
        System.out.println(user2s);
        return R.success().data("user2s",user2s);
    }

    @GetMapping(value = "/delete")
    public void delete() {
        int i = user2Mapper.deleteById(3);
        System.out.println("rowAffected = " + i);
    }

    //根据id 更新
    @GetMapping(value = "/test11")
    public void test11(@RequestParam("name") String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("name").eq("name", name).groupBy("id");
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
    public void test09(@RequestParam("name") String name) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, name);
        User user = new User();
        user.setAge(188);
        int number = userMapper.update(user, wrapper);
        System.out.println("更新条数" + number);
    }

    //根据 wrapper 条件，查询记录，将查询结果封装为一个Map，Map的key为结果的列，value为值
    @GetMapping(value = "/test08")
    public void test08(@RequestParam("name") String name) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "age").eq("name", name);
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
    }


    // 根据map中指定的列名和列值进行等值匹配 查找
    // 根据主键id进行查找
    @PostMapping(value = "/test07")
    public void test07(@RequestParam("map") HashMap<String, Object> map) {
        map.put("name", "qyy");
        List<User> userList = userMapper.selectByMap(map);
        for (User user : userList) {
            System.out.println(user);
        }
    }


    // 根据主键id进行查找
    @PostMapping(value = "/test06")
    public void test06(@RequestParam("list") List<Integer> list) {
//        ServerHttpRequest request = exchange.getRequest();
//
//        String path = request.getPath().toString();
//        String requestURI = request.getPath();
//        System.out.println(path);
//        String toString = requestURI.toString();
//        System.out.println(toString);


        List<User> userList = userMapper.selectBatchIds(list);
        for (User user : userList) {
            System.out.println(user);
        }
    }


    // 根据主键id进行查找
    @GetMapping(value = "/test05")
    public User test05(@RequestParam("id") Integer id) {
        User user = userMapper.selectById(id);
        System.out.println(user);
        return user;
    }


    // 根据条件构造器wrapper进行删除
    @GetMapping(value = "/test04")
    public void test04(@RequestParam("name") String name) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getName, name);
        userMapper.delete(wrapper);
    }


    // 根据ID删除数据
    @GetMapping(value = "/test03/{id}")
    public void test03(@PathVariable("id") Long id) {
        userMapper.deleteById(id);
    }

    // 插入一条记录
    @PostMapping(value = "/test02")
    @ApiOperation(value = "新增用户")
    public R test02(@RequestBody User user) {
//        User user = new User();
//        user.setName(name);
        userMapper.insert(user);
        return R.success();
    }

    /**
     * @Author: QYY
     * @Description: 查询数量匹配, 校验
     * 如果两者不一致, 程序继续往下运行
     * 如果两者一致, 中断测试方法, 抛出异常信息 AssertionFailedError
     * @DateTime: 2022/1/6 16:13
     * @Params: []
     * @Return void
     */
    @GetMapping(value = "/test01")
    public void testSelect() {

        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(6, list.size());
        Assert.assertNotEquals(6, list.size());
        list.forEach(System.out::println);
    }


}
