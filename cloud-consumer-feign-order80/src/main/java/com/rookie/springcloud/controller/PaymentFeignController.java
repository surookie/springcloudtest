package com.rookie.springcloud.controller;

import com.rookie.springcloud.entities.CommonResult;
import com.rookie.springcloud.entities.Payment;
import com.rookie.springcloud.service.PaymentFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 12:23
 * @Description:
 */
@Controller
public class PaymentFeignController {

//    @Autowired
    @Resource
    PaymentFeignService paymentFeignService;

    @ResponseBody
    @RequestMapping("/consumer/payment/get/{id}")
        public CommonResult<Payment> get(@PathVariable("id") Long id) {
            CommonResult<Payment> result = paymentFeignService.getPaymentById(id);
//            System.out.println(result);
            return result;
    }

    @ResponseBody
    @RequestMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return paymentFeignService.create(payment);
    }

    @ResponseBody
    @RequestMapping("/consumer/payment/port")
    public String getPort() {
        return paymentFeignService.getSort();
    }

}
