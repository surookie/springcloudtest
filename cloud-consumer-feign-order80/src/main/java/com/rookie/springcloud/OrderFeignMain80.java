package com.rookie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 11:55
 * @Description:
 */
@EnableEurekaClient //开启eureka的客户端
@EnableFeignClients //开启openFeign的远程调用
@SpringBootApplication
public class OrderFeignMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderFeignMain80.class, args);
    }
}
