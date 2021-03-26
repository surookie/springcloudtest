package com.rookie.springcloud.controller;


import com.rookie.springcloud.entities.CommonResult;
import com.rookie.springcloud.entities.Payment;
import com.rookie.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Controller
public class ConsumerController {

    private static final String URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private LoadBalancer loadBalancer; //添加自定义的

    @Autowired
    private DiscoveryClient discoveryClient;

    @ResponseBody //将响应的数据的以json的格式发送给客户端
    @GetMapping("/consumer/payment/get/{id}") //收到客户端的get请求,获取参数id
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        /*RestTemplate Spring框架封装的模板类，用于远程调用*/
        CommonResult result = restTemplate.getForObject(URL + "/payment/getPaymentById/" + id, CommonResult.class);
        return result;
    }

    @ResponseBody
    @PostMapping("/consumer/payment/create") //收到客户端的post请求,获取参数自动封装为Payment对象
    public CommonResult<Payment> create(Payment payment) {
        CommonResult result = restTemplate.postForObject(URL + "/payment/create", payment, CommonResult.class);
        return result;
    }

    @ResponseBody
    @GetMapping(value = "/consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        System.out.println("uri: " + uri);
        return restTemplate.getForObject(uri + "/payment/lb", String.class);
    }
}
