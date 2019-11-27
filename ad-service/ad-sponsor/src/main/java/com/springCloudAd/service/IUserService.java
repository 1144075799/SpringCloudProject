package com.springCloudAd.service;

import com.springCloudAd.exception.AdException;
import com.springCloudAd.vo.CreateUserRequest;
import com.springCloudAd.vo.CreateUserResponse;

public interface IUserService {

    /**
     * 创建用户
     * @param request
     * @return
     * @throws AdException
     */
    CreateUserResponse createUser(CreateUserRequest request) throws AdException;

}
