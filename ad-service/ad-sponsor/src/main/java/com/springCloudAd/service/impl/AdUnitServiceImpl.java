package com.springCloudAd.service.impl;

import com.springCloudAd.constant.Constants;
import com.springCloudAd.dao.AdPlanRepository;
import com.springCloudAd.dao.AdUnitRepository;
import com.springCloudAd.dao.CreativeRepository;
import com.springCloudAd.dao.unit_condition.AdUnitDistrictRepository;
import com.springCloudAd.dao.unit_condition.AdUnitItRepository;
import com.springCloudAd.dao.unit_condition.AdUnitKeywordRepository;
import com.springCloudAd.dao.unit_condition.CreativeUnitRepository;
import com.springCloudAd.entity.AdPlan;
import com.springCloudAd.entity.AdUnit;
import com.springCloudAd.entity.unit_condition.AdUnitDistrict;
import com.springCloudAd.entity.unit_condition.AdUnitIt;
import com.springCloudAd.entity.unit_condition.AdUnitKeyword;
import com.springCloudAd.entity.unit_condition.CreativeUnit;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IAdUnitService;
import com.springCloudAd.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdUnitServiceImpl implements IAdUnitService {

    private final AdPlanRepository planRepository;
    private final AdUnitRepository unitRepository;
    private final AdUnitKeywordRepository unitKeywordRepository;
    private final AdUnitItRepository unitItRepository;
    private final AdUnitDistrictRepository unitDistrictRepository;
    private final CreativeRepository creativeRepository;
    private final CreativeUnitRepository creativeUnitRepository;

    @Autowired
    public AdUnitServiceImpl(AdUnitRepository unitRepository, AdPlanRepository planRepository, AdUnitKeywordRepository unitKeywordRepository, AdUnitItRepository unitItRepository, AdUnitDistrictRepository unitDistrictRepository, CreativeRepository creativeRepository, CreativeUnitRepository creativeUnitRepository) {
        this.unitRepository = unitRepository;
        this.planRepository = planRepository;
        this.unitKeywordRepository = unitKeywordRepository;
        this.unitItRepository = unitItRepository;
        this.unitDistrictRepository = unitDistrictRepository;
        this.creativeRepository = creativeRepository;
        this.creativeUnitRepository = creativeUnitRepository;
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

    @Override
    public AdUnitKeywordResponse createUnitKeyword(AdUnitKeyWordRequest request) throws AdException {

        List<Long> unitIds = request.getUnitKeywords().stream()
                .map(AdUnitKeyWordRequest.UnitKeyword::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        List<Long> ids = Collections.emptyList();

        List<AdUnitKeyword> unitKeywords = new ArrayList<>();

        if (!CollectionUtils.isEmpty(request.getUnitKeywords())){
            request.getUnitKeywords().forEach(i -> unitKeywords.add(
                    new AdUnitKeyword(i.getUnitId(), i.getKeyword() )
            ));
            ids = unitKeywordRepository.saveAll(unitKeywords).stream()
                    .map(AdUnitKeyword::getId)
                    .collect(Collectors.toList());
        }

        return new AdUnitKeywordResponse(ids);
    }

    @Override
    public AdUnitItResponse createUnitIt(AdUnitItRequest request) throws AdException {

        List<Long> unitIds = request.getUnitIts().stream()
                .map(AdUnitItRequest.UnitIt::getUnitId)
                .collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        List<AdUnitIt> unitIts = new ArrayList<>();

        request.getUnitIts().forEach(i->unitIts.add(
                new AdUnitIt(i.getUnitId(),i.getItTag())
        ));

        List<Long> ids = unitItRepository.saveAll(unitIts).stream()
                .map(AdUnitIt::getId)
                .collect(Collectors.toList());


        return new AdUnitItResponse(ids);
    }

    @Override
    public AdUnitDistrictResponse createUnitDistric(AdUnitDistrictRequest request) throws AdException {

        List<Long> unitIds = request.getUnitDistricts().stream()
                .map(AdUnitDistrictRequest.UnitDistrict::getUnitId)
                .collect(Collectors.toList());

        if (!isRelatedUnitExist(unitIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        List<AdUnitDistrict> unitDistricts=new ArrayList<>();

        request.getUnitDistricts().forEach(i->unitDistricts.add(
                new AdUnitDistrict(i.getUnitId(),i.getProvince(),i.getProvince())
        ));

        List<Long> ids = unitDistrictRepository.saveAll(unitDistricts).stream()
                        .map(AdUnitDistrict::getId)
                        .collect(Collectors.toList());


        return new AdUnitDistrictResponse(ids);
    }

    @Override
    public CreativeUnitResponse createCreativeUnit(CreativeUnitRequest request) throws AdException {

        List<Long> unitIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        List<Long> creativeIds = request.getUnitItems().stream()
                .map(CreativeUnitRequest.CreativeUnitItem::getUnitId)
                .collect(Collectors.toList());
        if (!isRelatedUnitExist(unitIds) && !isRelatedCreateExist(creativeIds)){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }
        List<CreativeUnit> creativeUnits = new ArrayList<>();
        request.getUnitItems().forEach(i -> creativeUnits.add(
                new CreativeUnit(i.getCreativeId(),i.getUnitId())
        ));

        List<Long> ids = creativeUnitRepository.saveAll(creativeUnits)
                .stream()
                .map(CreativeUnit::getId)
                .collect(Collectors.toList());


        return new CreativeUnitResponse(ids);
    }

    private boolean isRelatedUnitExist(List<Long> unitIds){
        if (CollectionUtils.isEmpty(unitIds)){
            return false;
        }

        return unitRepository.findAllById(unitIds).size() ==
                new HashSet<>(unitIds).size();

    }

    private boolean isRelatedCreateExist(List<Long> creativeIds){

        if (CollectionUtils.isEmpty(creativeIds)){
            return false;
        }

        return creativeRepository.findAllById(creativeIds).size()==
                new HashSet<>(creativeIds).size();
    }

}
