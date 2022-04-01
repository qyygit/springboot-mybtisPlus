package com.atguigu.boot.mybatisPlus;

import com.atguigu.boot.bean.City;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.mapper.UserMapper;
import com.atguigu.boot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: QYY
 * @Description: TODO
 * @DateTime: 2022/1/10 10:57
 **/

@RestController
@Slf4j
@RequestMapping(value = "/plusService")
public class MyBatisPlusServerController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    //AR模式
    @GetMapping(value = "/activeRecord")
    public void activeRecord() {
        City city = new City();
        city.setId(6L);
        city.setName("6688秦洋洋");
        city.updateById();
    }

    @GetMapping(value = "/getPage")
    public void testPage() {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(User::getAge, 28);
        // 设置分页信息, 查第3页, 每页2条数据
        Page<User> page = new Page<>(1, 8);
        // 执行分页查询
        Page<User> userPage = userMapper.selectPage(page, wrapper);
        System.out.println("总记录数 = " + userPage.getTotal());
        System.out.println("总页数 = " + userPage.getPages());
        System.out.println("当前页码 = " + userPage.getCurrent());
        // 获取分页查询结果
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
    }


    // 链式写法,条件构造器
    @GetMapping(value = "/test06")
    public void test06(@RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "list", required = false) List<Integer> list) {

        LambdaQueryWrapper<Object> wrapper = new LambdaQueryWrapper<>();
        LambdaUpdateWrapper<Object> objectLambdaUpdateWrapper = new LambdaUpdateWrapper<>();

    }

    // 链式写法,条件构造器
    @GetMapping(value = "/test05")
    public void test05(@RequestParam(value = "age", required = false) Integer age,
                       @RequestParam(value = "name", required = false) String name,
                       @RequestParam(value = "list", required = false) List<Integer> list) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 名字模糊查询, 年龄小于 age
        // SELECT * FROM user WHERE name like '%佳%' AND age < 25
//        wrapper.like("name",name).lt("age",age);
//        List<User> userList = userService.list(wrapper);

        // 2. 姓名为黄姓，且年龄大于等于20，小于等于40，且email字段不为空
        // name like '黄%' AND age BETWEEN 20 AND 40 AND email is not null
//        wrapper.like("name",name).between("age",20,100).isNotNull("email").orderByDesc("gmt_create");
//        List<User> userList1 = userService.list(wrapper);


        // 3. 姓名为黄姓，或者年龄大于等于40，按照年龄降序排列，年龄相同则按照id升序排列
        //  name like '黄%' OR age >= 40 order by age desc, id asc
//        wrapper.likeRight("name",name).or().ge("age",age).orderByDesc("age").orderByAsc("id");
//        List<User> userList2 = userService.list(wrapper);
//        System.out.println(userList2);

        // 4.创建日期为2021年3月22日，并且直属上级的名字为李姓
        // date_format(create_time,'%Y-%m-%d') = '2021-03-22' AND manager_id IN (SELECT id FROM user WHERE name like '李%')
//        wrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", "2022-01-09") // 建议采用{index}这种方式动态传参, 可防止SQL注入
//                .inSql("id", "SELECT id FROM file WHERE fileName like '1%'");
        // 上面的apply, 也可以直接使用下面这种方式做字符串拼接，但当这个日期是一个外部参数时，这种方式有SQL注入的风险
//        wrapper.apply("date_format(create_time, '%Y-%m-%d') = '2021-03-22'");
//        List<User> list4 = userService.list(wrapper);

        // 5. 名字为王姓，并且（年龄小于40，或者邮箱不为空）
        //  name like '王%' AND (age < 40 OR email is not null)
//        wrapper.likeRight("name", name).and(q -> q.lt("age", age).or().isNotNull("email"));
//        List<User> userList = userService.list(wrapper);

        // 6. 名字为王姓，或者（年龄小于40并且年龄大于20并且邮箱不为空）
// name like '王%' OR (age < 40 AND age > 20 AND email is not null)
//        wrapper.likeRight("name", name).or(
//                q -> q.lt("age",20)
//                        .gt("age",100)
//                        .isNotNull("email"));


        // 7. (年龄小于40或者邮箱不为空) 并且名字为王姓
// (age < 40 OR email is not null) AND name like '王%'
//        wrapper.nested(q -> q.lt("age", age).or().isNotNull("email"))
//                .likeRight("name", name);
//        List<User> userList = userService.list(wrapper);

        // 8. 年龄为30，31，34，35
        // age IN (30,31,34,35)
//        wrapper.in("id", list);
//        String ids = "1,2,3,4";
//        wrapper.inSql("id",ids);
//        List<User> userList = userService.list(wrapper);


        // 9. 年龄为30，31，34，35, 返回满足条件的第一条记录
// age IN (30,31,34,35) LIMIT 1
//        wrapper.in("age", Arrays.asList(30,31,34,35)).last("LIMIT 1");
//        wrapper.in("age",list).last("LIMIT 1").isNotNull("email");
//        List<User> userList = userService.list(wrapper);
//        System.out.println(userList);


        // 10. 只选出id, name 列 (QueryWrapper 特有)
// SELECT id, name FROM user;
//        wrapper.select("id", "name");

// 11. 选出id, name, age, email, 等同于排除 manager_id 和 create_time
// 当列特别多, 而只需要排除个别列时, 采用上面的方式可能需要写很多个列, 可以采用重载的select方法，指定需要排除的列
//        wrapper.select(User.class, info -> {
//            String columnName = info.getColumn();
//            return !"create_time".equals(columnName) && !"manager_id".equals(columnName);
//        });

        // 假设name变量是一个外部传入的参数
//        wrapper.like(StringUtils.hasText(name), "name", name);
// 仅当 StringUtils.hasText(name) 为 true 时, 会拼接这个like语句到WHERE中
// 其实就是对下面代码的简化
//        if (StringUtils.hasText(name)) {
//            wrapper.like("name", name);
//        }
//        List<User> userList = userService.list(wrapper);


        // @TableField注解中的condition属性进行改变   @TableField(condition = SqlCondition.LIKE)   // 配置该字段使用like进行拼接
//        User user = new User();
//        user.setName(name);
//        user.setAge(age);
//        List<User> userList = userMapper.selectList(wrapper);


            // allEq方法传入一个map，用来做等值匹配
            Map<String, Object> param = new HashMap<>();
            param.put("age", age);
            param.put("name", name);
            wrapper.allEq(param);
            List<User> users = userMapper.selectList(wrapper);
            users.forEach(System.out::println);


    }


    // 链式写法,删除操作
    @GetMapping(value = "/test04")
    public void test04(@RequestParam("age") Integer age) {

        userService.lambdaUpdate().eq(User::getAge, age).remove();
    }

    // 链式写法,更新操作
    @GetMapping(value = "/test03/{age}")
    public void test03(@PathVariable("age") Integer age) {
        userService.lambdaUpdate().gt(User::getAge, age).set(User::getEmail, "w39@baomidou.com").update();
    }


    // 链式写法, 查询方式
    @PostMapping(value = "/test02")
    public void test02(@RequestParam("age") Integer age,
                       @RequestParam("name") String name) {
        List<User> list = userService.lambdaQuery().gt(User::getAge, age).likeRight(User::getName, name).list();
        list.forEach(System.out::println);
    }


    // 第二参数指定为false,使得在查到了多行记录时,不抛出异常,而返回第一条记录
    @GetMapping(value = "/test01")
    public void test01() {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.gt(User::getAge, 188);
        User userOne = userService.getOne(wrapper, false);
        System.out.println(userOne);
    }


}
