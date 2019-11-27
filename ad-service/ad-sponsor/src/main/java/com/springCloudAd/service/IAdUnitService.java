package com.springCloudAd.service;

import com.springCloudAd.exception.AdException;
import com.springCloudAd.vo.AdUnitRequest;
import com.springCloudAd.vo.AdUnitResponse;

public interface IAdUnitService {


    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

}
