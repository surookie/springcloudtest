package com.rookie.springcloud.service;

import com.rookie.springcloud.service.impl.PaymentHystrixServiceBeaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 15:25
 * @Description:通过@FeignClient指定远程调用的服务名，
 * 以及超时异常的降级类（自身实现类为重写的方法为兜底方法）
 *
 * 推荐：私有专属
 */
@FeignClient(value = "CLOUD-HYSTRIX-PAYMENT-SERVICE", fallback = PaymentHystrixServiceBeaker.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
