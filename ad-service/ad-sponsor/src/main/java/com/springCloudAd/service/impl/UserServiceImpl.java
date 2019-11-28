package com.springCloudAd.service.impl;

import com.springCloudAd.constant.Constants;
import com.springCloudAd.dao.AdUserRepository;
import com.springCloudAd.entity.AdUser;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IUserService;
import com.springCloudAd.utils.CommonUtils;
import com.springCloudAd.client.vo.CreateUserRequest;
import com.springCloudAd.client.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    private final AdUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AdUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) throws AdException {

        if (!request.validate()){
            throw new AdException(Constants.ErrorMsg.REQUEST_PARM_ERROR);
        }

        AdUser oldUser = userRepository.findByUsername(request.getUsername());

        if (oldUser != null){
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser=userRepository.save(new AdUser(
                request.getUsername(),
                CommonUtils.md5(request.getUsername())
        ));


        return new CreateUserResponse(
             newUser.getId(),newUser.getUsername(), newUser.getToken(),newUser.getCreateTime(),newUser.getUpdateTime()
        );
    }
}
