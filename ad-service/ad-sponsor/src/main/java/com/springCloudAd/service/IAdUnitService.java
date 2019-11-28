package com.springCloudAd.service;

import com.springCloudAd.exception.AdException;
import com.springCloudAd.vo.*;

public interface IAdUnitService {


    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeyWordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request)throws AdException;

    AdUnitDistrictResponse createUnitDistric(AdUnitDistrictRequest request)throws AdException;
}
