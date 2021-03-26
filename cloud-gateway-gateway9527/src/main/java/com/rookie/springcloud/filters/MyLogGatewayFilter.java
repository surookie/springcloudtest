package com.rookie.springcloud.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Auther: 自由者
 * @Date: 2021/3/26 08:22
 * @Description:
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    /*
    * @Author 自由者
    * @Description //练习自定义GlobalFilter，设定有username参数的值不为空时放行
    * @Date 8:24 2021/3/26
    * @Param [exchange, chain]
    * @return reactor.core.publisher.Mono<java.lang.Void>
    **/
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        log.info("Welcome to my filter..............." + new Date());
        if (StringUtils.isEmpty(username)) {
            log.info("用户名不能为空，非法请求");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();//将请求返回
        }
        return chain.filter(exchange);//放行
    }

    /*
    * @Author 自由者
    * @Description //设定优先级
    * @Date 8:36 2021/3/26
    * @Param []
    * @return int
    **/
    @Override
    public int getOrder() {
        return 0;
    }
}
