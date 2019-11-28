package com.springCloudAd.controller;

import com.alibaba.fastjson.JSON;
import com.springCloudAd.entity.AdPlan;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IAdPlanService;
import com.springCloudAd.client.vo.AdPlanGetRequest;
import com.springCloudAd.client.vo.AdPlanRequest;
import com.springCloudAd.client.vo.AdPlanResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AdPlanOPController {

    private final IAdPlanService adPlanService;

    @Autowired
    public AdPlanOPController(IAdPlanService adPlanService) {
        this.adPlanService = adPlanService;
    }

    @PostMapping("/create/adPlan")
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) throws AdException{
        log.info("ad-sponsor:createAdPlan->{}", JSON.toJSONString(request));

        return adPlanService.createPlan(request);
    }

    @PostMapping("/get/adPlan")
    public List<AdPlan> getAdPlanByIds(@RequestBody AdPlanGetRequest request) throws AdException{
        log.info("ad-sponsor:getAdPlanByIds->{}",JSON.toJSONString(request));

        return adPlanService.getAdPlanByIds(request);
    }

    @PutMapping("/update/adPlan")
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request)throws AdException{
        log.info("ad-sponsor:updateAdPlan->{}",JSON.toJSONString(request));

        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping("/delete/adPlan")
    public void deleteAdPlan(@RequestBody AdPlanRequest request)throws AdException{
        log.info("ad-sponsor:deleteAdPlan->{}",JSON.toJSONString(request));
    }

}
