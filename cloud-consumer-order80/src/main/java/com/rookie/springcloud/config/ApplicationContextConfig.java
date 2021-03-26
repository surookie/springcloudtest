package com.rookie.springcloud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced //ribbon 集成RestTemplate 实现--客户端--的负载均衡 又称软负载均衡（nginx是对于服务器端的负载均衡）
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
