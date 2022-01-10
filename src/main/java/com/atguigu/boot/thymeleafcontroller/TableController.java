package com.atguigu.boot.thymeleafcontroller;


import com.atguigu.boot.bean.User;
import com.atguigu.boot.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class TableController {

    @Autowired
    UserService userService;

//    根据ID删除用户
    @GetMapping("/user/delete/{id}")
    public String deleteUserById(@PathVariable(value = "id") Long id,
                                 @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                 RedirectAttributes ra){
        userService.removeById(id);
        //在那页删除,传递参数回到当前页,如果没数据,走默认值
        ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }

    @GetMapping(value = "/basic_table")
    public String basic_table(){
//        int i = 10/0;
        return "table/basic_table";
    }

    @GetMapping(value = "/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
//     List<User> users = Arrays.asList(new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("haha", "aaaaa"),
//                new User("hehe ", "aaddd"));
//        model.addAttribute("users",users);
        //构造分页参数
//        Page<User> page = new Page<>(pn, 2);
        //调用page进行分页
        List<User> list = userService.list();
        log.info("list" + list);
        Page<User> userPage = new Page<>(pn,2);
        Page<User> page = userService.page(userPage,null);

        model.addAttribute("page",page);
        return "table/dynamic_table";
    }

    @GetMapping(value = "/responsive_table")
    public  String responsive_table(){
        return "table/responsive_table";
    }

    @GetMapping(value = "/editable_table")
    public  String editable_table(){
        return "table/editable_table";
    }
}

