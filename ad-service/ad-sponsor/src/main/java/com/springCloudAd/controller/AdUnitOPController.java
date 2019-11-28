package com.springCloudAd.controller;

import com.alibaba.fastjson.JSON;
import com.springCloudAd.dao.unit_condition.AdUnitItRepository;
import com.springCloudAd.dao.unit_condition.AdUnitKeywordRepository;
import com.springCloudAd.entity.unit_condition.AdUnitDistrict;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IAdUnitService;
import com.springCloudAd.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AdUnitOPController {

    private final IAdUnitService adUnitService;

    @Autowired
    public AdUnitOPController(IAdUnitService adUnitService) {
        this.adUnitService = adUnitService;
    }

    @PostMapping("/create/adUnit")
    public AdUnitResponse createUnit(@RequestBody AdUnitRequest request) throws AdException {
        log.info("ad-sponsor:createUnit->{}", JSON.toJSONString(request));

        return adUnitService.createUnit(request);
    }

    @PostMapping("/create/unitKeyword")
    public AdUnitKeywordResponse createUnitKeyword(@RequestBody AdUnitKeyWordRequest request) throws AdException {
        log.info("ad-sponsor:createUnitKeyword->{}", JSON.toJSONString(request));
        return adUnitService.createUnitKeyword(request);
    }

    @PostMapping("/create/unitIt")
    public AdUnitItResponse createUnitIt(@RequestBody AdUnitItRequest request)throws AdException{
        log.info("ad-sponsor:createUnitIt->{}", JSON.toJSONString(request));
        return adUnitService.createUnitIt(request);
    }

    @PostMapping("/create/unitDistrict")
    public AdUnitDistrictResponse createUnitDistrict(@RequestBody AdUnitDistrictRequest request)throws  AdException{
        log.info("ad-sponsor:createUnitDistrict->{}", JSON.toJSONString(request));
        return adUnitService.createUnitDistric(request);
    }
}
