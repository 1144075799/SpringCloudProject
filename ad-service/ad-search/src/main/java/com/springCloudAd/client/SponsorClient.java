package com.springCloudAd.client;

import com.springCloudAd.client.vo.AdPlan;
import com.springCloudAd.client.vo.AdPlanGetRequest;
import com.springCloudAd.client.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "eureka-client-ad-sponsor",fallback = SponsorClientHystrix.class)
public interface SponsorClient {

    @RequestMapping(value = "/ad-sponsor/get/adPlan",method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlan(
            @RequestBody AdPlanGetRequest request);

}
