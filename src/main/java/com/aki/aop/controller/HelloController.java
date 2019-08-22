package com.aki.aop.controller;

import com.aki.aop.zhujie.MyAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @MyAnnotation
    @RequestMapping("/add1")
    public String addData1(String deviceId) {
        return "success";
    }

    @RequestMapping("/add2")
    public String addData2(String deviceId) {
        return "success";
    }

    @RequestMapping("/add3")
    public String addData3(String deviceId) {
        return "success";
    }

}