package com.rookie.springcloud.service;

import com.rookie.springcloud.entities.Payment;


public interface PaymentService {
    public int create(Payment payment); //写
    public Payment getPaymentById(Long id);  //读取
}
