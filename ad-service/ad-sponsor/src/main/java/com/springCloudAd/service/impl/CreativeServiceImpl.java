package com.springCloudAd.service.impl;

import com.springCloudAd.dao.CreativeRepository;
import com.springCloudAd.entity.Creative;
import com.springCloudAd.service.ICreativeService;
import com.springCloudAd.client.vo.CreativeRequest;
import com.springCloudAd.client.vo.CreativeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreativeServiceImpl implements ICreativeService {

    private final CreativeRepository creativeRepository;

    @Autowired
    public CreativeServiceImpl(CreativeRepository creativeRepository) {
        this.creativeRepository = creativeRepository;
    }

    @Override
    public CreativeResponse createCreative(CreativeRequest request) {

        Creative creative = creativeRepository.save(
                request.convertToEntity()
        );

        return new CreativeResponse(creative.getId(),creative.getName());
    }
}
