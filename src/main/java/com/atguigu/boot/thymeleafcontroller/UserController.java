package com.atguigu.boot.thymeleafcontroller;

import com.atguigu.boot.bean.User;
import com.atguigu.boot.mapper.UserMapper;
import com.atguigu.boot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    // 10. 只选出id, name 列 (QueryWrapper 特有)
    // SELECT id, name FROM user;
    @PostMapping("/testFindBySql3")
    public void testFindBySql3(@RequestBody User user) {

        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
    // 10. 只选出id, name 列 (QueryWrapper 特有)
    // SELECT id, name FROM user;
    @GetMapping("/testFindBySql2")
    public void testFindBySql2(@RequestParam("age") Integer age) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select(User.class, info -> {
            String columnName = info.getColumn();
            return !"create_time".equals(columnName) && !"id".equals(columnName);
        });

        List<User> list = userService.list(wrapper);
        System.out.println(list);

    }

    // 10. 只选出id, name 列 (QueryWrapper 特有)
    // SELECT id, name FROM user;
    @GetMapping("/testFindBySql")
    public void testFindBySql(@RequestParam("age") Integer age) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name").eq("age", age);
        List<User> list = userService.list(wrapper);
        System.out.println(list);
    }

    //传递集合参数
    @GetMapping("/testList")
    public void testList(@RequestParam("age") Integer age) {
        ArrayList<Object> ageList = new ArrayList<>();
        ageList.add(1);
        ageList.add(2);
        ageList.add(77);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.in("age", ageList);
        List<User> list = userService.list(wrapper);
        System.out.println(list);

    }


    @GetMapping("/testFind")
    public void testFind(@RequestParam("age") Integer age,
                         @RequestParam("name") String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", name).and(q -> q.lt("age", age).or().isNotNull("email"));
        List<User> list = userService.list(wrapper);
        System.out.println(list);

    }


    //UserService 方法
    //删除
    @GetMapping("/testSql")
    public void testSql() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", "2021-12-09")  // 建议采用{index}这种方式动态传参, 可防止SQL注入
                .inSql("id", "SELECT id FROM user WHERE name like '黄%'");
        List<User> list = userService.list(wrapper);
        System.out.println(list);
    }


    //UserService 方法
    //删除
    @GetMapping("/testDelete")
    public void testDelete(@RequestParam("age") Integer age,
                           @RequestParam("name") String name) {
        userService.lambdaUpdate().eq(User::getAge, age).likeRight(User::getName, name).remove();
    }


    //UserService 方法
    //更新
    @GetMapping("/testUpdate")
    public void testUpdate(@RequestParam("ages") Integer age,
                           @RequestParam("names") String name) {
        userService.lambdaUpdate().gt(User::getAge, age).likeRight(User::getName, name).set(User::getEmail, "w39@baomidou.com").update();
    }


    //UserService 方法
    //链式写法
    @GetMapping("/test9")

    public void test9() {
        List<User> list = userService.lambdaQuery().gt(User::getAge, 39).likeRight(User::getName, "逻辑").list();
        list.forEach(System.out::println);
    }


    //UserService 方法
    // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录  System.out.println(one); }}复制代码
    @GetMapping("/test8")
    public void test8(@RequestParam("age") Integer age) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.gt(User::getAge, age);
        User oneUser = userService.getOne(wrapper, true);
        System.out.println(oneUser);
    }

    //XML -sql
    @GetMapping("/test7")
    public void test7() {
        List<User> userList = userService.selectRaw();
        System.out.println(userList);
    }


    //注解SQL
    @GetMapping("/test6")
    public void test6() {
        List<User> userList = userService.findBySql();
        System.out.println(userList);
    }


    //更新较少字段
    @GetMapping("/test5")
    public void test5() {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getName, "黄主管").set(User::getAge, 111);
        boolean update = userService.update(wrapper);
        System.out.println(update);
    }


    // 根据ID更新
    @GetMapping("/test4")
    public void test4() {
        User user = new User();
        user.setId(5L);
        user.setAge(2222222);
        boolean b = userService.updateById(user);
    }


    @GetMapping("/testCount3")
    public void testCount3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("age", 40);
        param.put("name", "黄飞飞");
        wrapper.allEq(param);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    // 等值匹配,验证身份
    @GetMapping("/testCount2")
    public void testCount2() {
        User user = new User();
//        user.setName("黄主管");
//        user.setAge(28);
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }


    //拼写sql
    @GetMapping("/testCount")
    public void testCount() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeRight("name", "逻辑").isNull("age");
        List<User> list = userService.list(wrapper);
        System.out.println(list);
    }


    //拼写sql
    @GetMapping("/selectBySql")
    public void selectBySql() {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        QueryWrapper<User> userQueryWrapper = wrapper.select("name as userName", "count(*) as number").groupBy("name");
        Map<String, Object> map = userService.getMap(wrapper);
        System.out.println(map);
    }

    @GetMapping("/selectByMap")
    public void userMapper(@RequestParam("str") String str) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", str);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @RequestMapping("/selectMap")
    public void selectMap(@RequestParam("str") String str) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "email").likeRight("name", str);
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @RequestMapping("/saveBatch")
    public void saveTest() {

        //1,创建一个集合
        List<String> list = Arrays.asList("a", "b", "c");
        //2 ,创建一个顺序流
        Stream<String> stream = list.stream();
        List<User> entityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
//            user.setName("aaa");
            user.setAge(18);
            user.setEmail("aaa");
            entityList.add(user);
        }
        userService.saveBatch(entityList);
    }


}
