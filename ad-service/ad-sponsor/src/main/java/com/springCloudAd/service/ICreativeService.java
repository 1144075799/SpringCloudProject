package com.springCloudAd.service;

import com.springCloudAd.client.vo.CreativeRequest;
import com.springCloudAd.client.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);

}
