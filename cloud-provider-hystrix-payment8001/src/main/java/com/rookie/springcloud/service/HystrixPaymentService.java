package com.rookie.springcloud.service;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 14:48
 * @Description:
 */
public interface HystrixPaymentService {
    public String paymentInfo_OK(Integer id);
    public String payment_Timeout(Integer id);

    public String paymentCircuitBreaker(Integer id);
}
