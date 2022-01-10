package com.atguigu.boot.controller;

//import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {

    //路径变量 , 通过  @PathVariable 注解获取 路径中的username 绑定到 String name 参数上
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     //路径参数的封装
                                     @PathVariable("username") String name,
                                     @PathVariable Map<String ,String> pv,
                                     //获取请求头
                                     @RequestHeader("User-Agent") String userAgent,
                                     // 获取所有请求头,放到map中
                                     @RequestHeader Map<String,String> header,
                                     //获取请求的参数信息
                                     @RequestParam("age") Integer age,
                                     @RequestParam("inters")List<String>inters,
                                     //获取所有参数信息
                                     @RequestParam Map<String,String> param
//                                     @CookieValue("_ga")  String _ga
//                                     @CookieValue("_ga") Cookie cookie

                                     ){
        Map<String,Object> map = new HashMap<>();
//        map.put("id",id);
//        map.put("name",name);
//        map.put("pv",pv);
//        map.put("userAgent",userAgent);
//        map.put("header",header);
//        map.put("age",age);
//        map.put("inters",inters);
//        map.put("param",param);

//        map.put("_ga",_ga);
//        map.put("cookie" ,cookie);
        return map;
    }


    @RequestMapping(value="/save",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    public Map postMeyhod(@RequestBody String context){


        Map<String,Object> map = new HashMap<>();
        map.put("context",context);
        return map;
    }
}
