package com.atguigu.boot.thymeleafcontroller;

import com.atguigu.boot.base.BaseController;
import com.atguigu.boot.bean.Account;
import com.atguigu.boot.bean.City;
import com.atguigu.boot.bean.User;
import com.atguigu.boot.common.result.R;
import com.atguigu.boot.dozer.DozerUtils;
import com.atguigu.boot.mapper.CityMapper;
import com.atguigu.boot.service.AccountService;
import com.atguigu.boot.service.UserService;
import com.atguigu.boot.service.impl.CityServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class IndexController extends BaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;




    @Autowired
    AccountService accountService;

    @Autowired
    CityServiceImpl cityServiceImpl;

    @Autowired
    CityMapper cityMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private DozerUtils dozerUtils;


    //    mybatis 测试,纯注解调用
    @GetMapping(value = "/saveUser")
    public R saveUser() {


        Page<Object> page = getPage();
        User user = new User();
//        user.setName("ceshi");
        boolean save = userService.save(user);
       return R.success();
    }


    //    mybatis 测试,纯注解调用
    @GetMapping(value = "/city")
    @ResponseBody
    public City getById(Long id) {
        City city = cityServiceImpl.getById(id);
        log.info("根据ID查询城市信息" + city);
        return city;
    }

    //    mybatis 测试, 根据ID查询返回
    @GetMapping(value = "/acct")
    @ResponseBody
    public Account findById(@RequestParam("id") Long id) {
        Account account = accountService.getAccountByiD(id);
        log.info("返回数据" + account);
        return account;
    }

    @GetMapping(value = "/sql")
    @ResponseBody
    public String queryForObject() {
        Long aLong = jdbcTemplate.queryForObject("select count(*) from animal", Long.class);
        log.info("记录总数：{}", aLong);
//        log.info("数据源类型：{}",dataSource.getClass());
        return aLong.toString();
    }

    //    来登录页
    @GetMapping(value = {"/", "/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String main(User user, HttpSession session, Model model) {

        if (StringUtils.isNotEmpty(user.getUserName()) && "123456".equals(user.getPassword()
        )) {
            session.setAttribute("loginUser", user);
            //登录成功重定向到main.html;  重定向防止表单重复提交
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "账号密码错误");
            return "redirect:/login.html";
        }
    }

    @GetMapping(value = "/main.html")
    public String mainPage(HttpSession session, Model model) {
        log.info("当前方法是：{}", "mainPage");
//      Object loginUser =  session.getAttribute("loginUser");
//        if (loginUser !=null){
//            return "main";
//        }else {
//            model.addAttribute("msg","未登录,请重新登录");
//            return "login";
//        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String s1 = operations.get("/main.html");
        String s2 = operations.get("/sql");
        long l1 = Long.parseLong(s1);
        int parseInt = Integer.parseInt(s2);
        model.addAttribute("mainCount", s1);
        model.addAttribute("sqlCount", s2);
        return "main";
    }
}
