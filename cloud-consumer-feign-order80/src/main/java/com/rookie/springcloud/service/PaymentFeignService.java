package com.rookie.springcloud.service;

import com.rookie.springcloud.entities.CommonResult;
import com.rookie.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 11:59
 * @Description:
 */
@Component
@FeignClient("CLOUD-PAYMENT-SERVICE") //指定要访问的服务端在eureka注册的名字
public interface PaymentFeignService {

    //定义的方法名要与远程调用的方法保持一致
    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment);

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/port")
    public String getSort();
}
