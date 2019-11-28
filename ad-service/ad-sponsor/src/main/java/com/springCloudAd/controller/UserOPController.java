package com.springCloudAd.controller;

import com.alibaba.fastjson.JSON;
import com.springCloudAd.exception.AdException;
import com.springCloudAd.service.IUserService;
import com.springCloudAd.vo.CreateUserRequest;
import com.springCloudAd.vo.CreateUserResponse;
import com.sun.deploy.ui.AboutDialog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserOPController {

    private final IUserService userService;

    @Autowired
    public UserOPController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create/user")
    public CreateUserResponse createUserResponse(@RequestBody CreateUserRequest request) throws AdException {

        log.info("ad-sponsor:createUser->{}", JSON.toJSONString(request));

        return userService.createUser(request);

    }
}
