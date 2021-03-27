package com.rookie.springcloud.controller;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 21:42
 * @Description:
 */
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
public class FlowLimitController{
    @GetMapping("/testA")
    public String testA() {
        return "------testA" + new Date();
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB" + new Date();
    }
}
