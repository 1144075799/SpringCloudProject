package com.springCloudAd.advice;

import com.springCloudAd.exception.AdException;
import com.springCloudAd.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 当我们的代码抛出AdException的时候
     * 就会被我们handlerAdException捕获进行处理
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest request,
                                                     AdException exception){
        CommonResponse<String> response=new CommonResponse<>(-1,"business error");

        response.setData(exception.getMessage());
        return response;
    }
}
