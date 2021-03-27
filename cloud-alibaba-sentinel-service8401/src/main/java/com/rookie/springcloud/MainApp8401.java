package com.rookie.springcloud;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 21:42
 * @Description:
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MainApp8401{
    public static void main(String[] args) {
        SpringApplication.run(MainApp8401.class, args);
    }
}
