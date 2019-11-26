package com.springCloudAd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrePequestFilter extends ZuulFilter {
    /**
     * 定义Filter类型
     *
     * @return
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 定义filter执行的顺序
     * 数字越小表示顺序越高，先被执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *是否要执行过滤器
     * true 要是执行，默认是不执行
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * filter要实现的方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        //  这次请求用到的请求上下文
        RequestContext requestContext=RequestContext.getCurrentContext();
        requestContext.set("startTime",System.currentTimeMillis());


        return null;
    }
}
