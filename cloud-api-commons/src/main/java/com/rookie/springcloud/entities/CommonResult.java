package com.rookie.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

//将处理后的信息封装到该类中，方便输出、重复利用
public class CommonResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}

