package com.rookie.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rookie.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 15:27
 * @Description: 测试消费端的降级演示
 */
//优点：普通方法都可使用一个兜底方法缺：耦合混乱
//@DefaultProperties(defaultFallback = "globalDemoting")
@Slf4j
@RestController
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;


    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("*******result:" + result);
        return result;
    }

    /*@HystrixCommand(fallbackMethod = "OrderPaymentTimeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })*/
    /*
    * @Author 自由者
    * @Description //推荐在使用ResTemplate远程调用时，使用@HystrixCommand和@DefaultProperties（全局默认降级方法）接口
    * 使用openFeign组件时，使用处理类方法进行降级处理
    * @Date 17:05 2021/3/25
    * @Param [id]
    * @return java.lang.String
    **/
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        System.out.println("----------------101------------------");
//        int i = 1 / 0;
        System.out.println("----------------4444------------------");
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("*******result:" + result);
        return result;
    }

    //兜底方法，遇到超时异常，调用该方法
    public String OrderPaymentTimeout(Integer id) {
        return "80服务器出现异常，请联系管理官解决。";
    }


    /*
    * @Author 自由者
    * @Description //设置全局降级方法
    * @Date 16:47 2021/3/25
    * @Param
    * @return
    **/
    public String globalDemoting() {
        return "官方话，服务异常。";
    }
}
