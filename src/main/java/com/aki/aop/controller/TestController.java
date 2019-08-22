package com.aki.aop.controller;


import com.aki.aop.zhujie.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @SysLog("测试")
    @GetMapping("/test")
    public String test(@RequestParam("name") String name){
        System.out.println("执行方法 test");
        return name;
    }
}
