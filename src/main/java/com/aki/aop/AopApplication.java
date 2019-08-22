package com.aki.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);

        //http://localhost:8080/add1?deviceId=03    显示success
        //http://localhost:8080/add1?deviceId=01    显示no anthorization


        //http://localhost:8080/test?name=aki    显示aki
    }

}
