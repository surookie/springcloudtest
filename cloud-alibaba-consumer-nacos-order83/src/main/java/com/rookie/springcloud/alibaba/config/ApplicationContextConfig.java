package com.rookie.springcloud.alibaba.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 17:21
 * @Description:
 */
@SpringBootConfiguration
public class ApplicationContextConfig {


    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
