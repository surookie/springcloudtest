package com.rookie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 17:15
 * @Description:
 */
@EnableDiscoveryClient
@SpringBootApplication
public class OrderNacosMain83 {
    public static void main(String args[]) {
        SpringApplication.run(OrderNacosMain83.class, args);
    }
}
