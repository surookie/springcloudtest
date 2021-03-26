package com.rookie.springcloud.service.impl;

import com.rookie.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 16:53
 * @Description: 这是一个专门放置兜底方法的类
 */
@Component
public class PaymentHystrixServiceBeaker implements PaymentHystrixService {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK异常";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut异常";
    }
}
