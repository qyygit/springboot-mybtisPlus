package com.atguigu.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewTestController {

    @RequestMapping("/thymeleaf")
    public String testThymeleaf(Model model) {
        model.addAttribute("msg", "hello ,你好");
        model.addAttribute("link", "www.baidu.com");
        return "success";
    }
}
