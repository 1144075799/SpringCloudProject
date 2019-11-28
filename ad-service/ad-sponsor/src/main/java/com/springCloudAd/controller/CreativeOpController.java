package com.springCloudAd.controller;

import com.alibaba.fastjson.JSON;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.ICreativeService;
import com.springCloudAd.client.vo.CreativeRequest;
import com.springCloudAd.client.vo.CreativeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CreativeOpController {

    private final ICreativeService creativeService;

    @Autowired
    public CreativeOpController(ICreativeService creativeService) {
        this.creativeService = creativeService;
    }
    @PostMapping("/create/creative")
    public CreativeResponse createCreative(@RequestBody CreativeRequest request) throws AdException{
        log.info("ad-sponsor:createCreative->{}", JSON.toJSONString(request));
        return creativeService.createCreative(request);
    }
}
