package com.rookie.springcloud.controller;

import com.rookie.springcloud.entities.CommonResult;
import com.rookie.springcloud.entities.Payment;
import com.rookie.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //服务提供者不进行页面跳转操作，因此可以@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;
    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        try {
            int i = paymentService.create(payment);
            if (i <= 0) {
                log.error("插入数据失败：" + payment);
                return new CommonResult<Payment>(40000, "插入数据失败:" + port, payment);
            } else {
                log.error("插入数据成功：" + payment);
                return new CommonResult<Payment>(20000, "插入数据成功" + port, payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("插入数据异常：" + e.getMessage());
            return new CommonResult<Payment>(99999, "插入数据异常");
        }

    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        try {
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
                log.error("查无此项：id=" + id);
                return new CommonResult<Payment>(40000, port + "查无此项：id=" + id);
            } else {
                log.error("查询成功:" + payment);
                return new CommonResult<>(20000, "查询成功" + port, payment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询数据异常:" + e.getMessage());
            return new CommonResult<>(99999, "查询数据异常" + e.getMessage());
        }
    }


    @GetMapping("/payment/port/{id}")
    public String getSort(@PathVariable("id") Integer id) {
       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return port + ",port,id=" + id;
    }

    @GetMapping("/payment/lb/{id}")

    public String sort(@PathVariable("id") Integer id) { /*, @RequestParam("password") String username*/
        return port + ",lb,id=" + id;
        /*return port + ",lb,username=" + username + ",id=" + id;*/
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi ,i'am paymentzipkin server，welcome to atguigu，O(∩_∩)O哈哈~";
    }

    @GetMapping("/payment/test")
    public String test(){
        return "test..........";
    }
}
