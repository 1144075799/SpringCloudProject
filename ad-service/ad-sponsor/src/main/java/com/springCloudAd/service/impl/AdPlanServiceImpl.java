package com.springCloudAd.service.impl;

import com.springCloudAd.constant.CommonStatus;
import com.springCloudAd.constant.Constants;
import com.springCloudAd.dao.AdPlanRepository;
import com.springCloudAd.dao.AdUserRepository;
import com.springCloudAd.entity.AdPlan;
import com.springCloudAd.entity.AdUser;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IAdPlanService;
import com.springCloudAd.utils.CommonUtils;
import com.springCloudAd.vo.AdPlanGetRequest;
import com.springCloudAd.vo.AdPlanRequest;
import com.springCloudAd.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdPlanService {

    private final AdUserRepository userRepository;

    private final AdPlanRepository planRepository;

    @Autowired
    public AdPlanServiceImpl(AdUserRepository userRepository, AdPlanRepository planRepository) {
        this.userRepository = userRepository;
        this.planRepository = planRepository;
    }


    @Override
    @Transactional
    public AdPlanResponse createPlan(AdPlanRequest request) throws AdException {

        if (!request.createValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        // 确保关联的User对象 存在
        Optional<AdUser> adUser = userRepository.findById(request.getId());

        if (adUser.isPresent()){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(
                request.getUserId(),request.getPlanName()
        );

        if (oldPlan != null){
            throw new AdException(Constants.ErrorMsg.SAME_PLAN_ERROR);
        }

        AdPlan newAdPlan = planRepository.save(
                new AdPlan(request.getUserId(),request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate()))
        );

        return new AdPlanResponse(newAdPlan.getId(),newAdPlan.getPlanName());
    }

    @Override
    @Transactional
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException {

        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        return planRepository.findAllByIdInAndUserId(request.getIds(),request.getUserId());
    }

    @Override
    @Transactional
    public AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException {

        if (!request.updateValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(),request.getUserId()
        );

        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null){
            plan.setPlanName(request.getPlanName());
        }

        if (request.getStartDate() != null){
            plan.setStartDate(
                    CommonUtils.parseStringDate(request.getStartDate())
            );
        }

        if (request.getEndDate() != null){
            plan.setEndDate(
                    CommonUtils.parseStringDate(request.getEndDate())
            );
        }

        plan.setUpdateTime(new Date());

        plan = planRepository.save(plan);


        return new AdPlanResponse(plan.getId(),plan.getPlanName());
    }

    @Override
    @Transactional
    public void deleteAdPlan(AdPlanRequest request) throws AdException {
        if (!request.deleteValidate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(
                request.getId(),request.getUserId()
        );

        if (plan == null){
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
