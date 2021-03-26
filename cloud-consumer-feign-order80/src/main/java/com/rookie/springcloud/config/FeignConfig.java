package com.rookie.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 13:21
 * @Description: 测试openFeign的日志功能
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }
}
