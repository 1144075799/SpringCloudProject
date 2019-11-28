package com.springCloudAd.client.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一响应
 */
@Data                       //Get Set方法
@NoArgsConstructor          //无参构造
@AllArgsConstructor         //带参构造
public class CommonResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
