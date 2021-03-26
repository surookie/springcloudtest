package com.rookie.springcloud.test;

import java.time.ZonedDateTime;

/**
 * @Auther: 自由者
 * @Date: 2021/3/25 22:32
 * @Description:
 */
public class TestTime {
    public static void main(String args[]) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
