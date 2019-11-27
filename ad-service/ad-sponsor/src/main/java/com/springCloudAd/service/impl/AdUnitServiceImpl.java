package com.springCloudAd.service.impl;

import com.springCloudAd.constant.Constants;
import com.springCloudAd.dao.AdPlanRepository;
import com.springCloudAd.dao.AdUnitRepository;
import com.springCloudAd.entity.AdPlan;
import com.springCloudAd.entity.AdUnit;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IAdUnitService;
import com.springCloudAd.vo.AdUnitRequest;
import com.springCloudAd.vo.AdUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdUnitServiceImpl implements IAdUnitService {

    private final AdPlanRepository planRepository;

    private final AdUnitRepository unitRepository;

    @Autowired
    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
    }

    @Override
    public AdUnitResponse createUnit(AdUnitRequest request) throws AdException {

        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        Optional<AdPlan> adPlan = planRepository.findById(request.getPlanId());

        if (!adPlan.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdUnit oldUnit = unitRepository.findByPlanIdAndUnitName(
                request.getPlanId(),request.getUnitName()
        );

        if (oldUnit != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_UNIT_ERROR);
        }

        AdUnit newAdUnit = unitRepository.save(new AdUnit(
            request.getPlanId(),request.getUnitName(),request.getPositionType(),request.getBudget()
        ));


        return new AdUnitResponse(newAdUnit.getId(),newAdUnit.getUnitName());
    }
}
