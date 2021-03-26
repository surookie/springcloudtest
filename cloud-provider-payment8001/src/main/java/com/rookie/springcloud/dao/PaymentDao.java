package com.rookie.springcloud.dao;

import com.rookie.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component //代替@Repository声明bean
@Mapper
//@Repository :如果要使用这个注解，则需要另外在启动类上加上MapperScan("dao层的全类名")
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
