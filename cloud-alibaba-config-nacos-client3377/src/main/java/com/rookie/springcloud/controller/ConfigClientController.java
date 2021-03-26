package com.rookie.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 17:43
 * @Description:
 */
@RestController
@RefreshScope   //通过SpringCould原生注解@RefreshScope实现配置自动更新
public class ConfigClientController{
    @Value("${config.info}") //对应nacos配置:nacos-config-client-dev.yaml,从配置中心拿
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
