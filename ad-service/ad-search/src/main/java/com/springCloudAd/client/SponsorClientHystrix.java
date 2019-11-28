package com.springCloudAd.client;

import com.springCloudAd.client.vo.AdPlan;
import com.springCloudAd.client.vo.AdPlanGetRequest;
import com.springCloudAd.client.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SponsorClientHystrix implements SponsorClient {


    @Override
    public CommonResponse<List<AdPlan>> getAdPlan(AdPlanGetRequest request) {

        return new CommonResponse<>(-1,
                "eureka-client-ad-sponsor error");
    }
}
