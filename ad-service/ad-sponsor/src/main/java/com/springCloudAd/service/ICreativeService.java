package com.springCloudAd.service;

import com.springCloudAd.vo.CreativeRequest;
import com.springCloudAd.vo.CreativeResponse;

public interface ICreativeService {

    CreativeResponse createCreative(CreativeRequest request);

}
