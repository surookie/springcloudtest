package com.rookie.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 16:56
 * @Description:
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String sortPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPorts(@PathVariable("id") Integer id) {
        return "nacos......id=" + id;
    }

    @GetMapping("/payment/test")
    public String test() {
        return "test..............";
    }
}
