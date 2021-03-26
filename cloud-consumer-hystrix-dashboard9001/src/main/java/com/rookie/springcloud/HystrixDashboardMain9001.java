package com.rookie.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 20:30
 * @Description:
 */
@EnableHystrixDashboard //启动仪表板
@SpringBootApplication
public class HystrixDashboardMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardMain9001.class, args);
    }


}
