package com.rookie.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rookie.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 14:49
 * @Description:
 */
@Service
public class HystrixPaymentServiceImpl implements HystrixPaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_OK,id：  " + id + "\t" + "哈哈哈";
    }


    /*
     * @Author 自由者
     * @Description //Hystrix降级演示
     * fallbackMethod ：设置降级方法（兜底方法）
     * commandProperties ：
     * @Date 16:01 2021/3/25
     * @Param [id]
     * @return java.lang.String
     **/
    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String payment_Timeout(Integer id) {
        int timeNumber = 3;
//        int timeNumber = 13;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "   paymentInfo_TimeOut,id：  " + id + "\t" + "呜呜呜" + " 耗时(秒)" + timeNumber;
    }

    //降级方法：兜底方法，向客户显示友好提示，返回值和参数要与被替换的方法保持一致
    public String paymentTimeout(Integer id) {
        return "8001服务id :" + id + "除了问题，请联系管理员";
    }



    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //当在配置时间窗口内达到此数量的失败后，打开断路，默认20个
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //断路多久以后开始尝试是否恢复，默认5s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //出错百分比阈值，当达到此阈值后，开始短路。默认50%
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();//hutool.cn工具包

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        String serialNumber = IdUtil.simpleUUID();//hutool.cn工具包
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     数字: " +serialNumber;
    }
}
