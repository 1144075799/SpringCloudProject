package com.springCloudAd.service;

import com.springCloudAd.exception.AdException;
import com.springCloudAd.client.vo.*;

public interface IAdUnitService {


    AdUnitResponse createUnit(AdUnitRequest request) throws AdException;

    AdUnitKeywordResponse createUnitKeyword(AdUnitKeyWordRequest request) throws AdException;

    AdUnitItResponse createUnitIt(AdUnitItRequest request)throws AdException;

    AdUnitDistrictResponse createUnitDistric(AdUnitDistrictRequest request)throws AdException;

    CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException;
}
