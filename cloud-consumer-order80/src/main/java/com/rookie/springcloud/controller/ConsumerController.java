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

    /*
    * @Author 自由者
    * @Description //测试自定义负载均衡算法，LoadBalancer
    * @Date 13:45 2021/3/26
    * @Param [id]
    * @return java.lang.String
    **/
    @ResponseBody
    @GetMapping(value = "/consumer/payment/lb/{id}")
    public String getPaymentLB(@PathVariable("id") Integer id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        System.out.println("uri: " + uri);
        return restTemplate.getForObject(uri + "/payment/lb/" + id , String.class);
    }

    /*
    * @Author 自由者
    * @Description //测试zipkin
    * @Date 13:46 2021/3/26
    * @Param []
    * @return java.lang.String
    **/
    @ResponseBody
    @GetMapping("/consumer/payment/zipKin")
    public String zipKin() {
        String result = restTemplate.getForObject(URL + "/payment/zipkin/", String.class);
        System.out.println(result);
        return result;
    }

    /*测试连接是否异常*/
    @ResponseBody
    @GetMapping("/consumer/payment/test")
    public String test() {
        System.out.println("-------------------------");
        String result = restTemplate.getForObject(URL + "/payment/test", String.class);
        System.out.println(result);
        return result;
    }
}
