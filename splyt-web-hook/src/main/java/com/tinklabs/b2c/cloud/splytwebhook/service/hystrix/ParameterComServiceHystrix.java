package com.tinklabs.b2c.cloud.splytwebhook.service.hystrix;

import com.tinklabs.b2c.cloud.splytwebhook.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.splytwebhook.entity.Company;
import com.tinklabs.b2c.cloud.splytwebhook.service.CacheComService;
import com.tinklabs.b2c.cloud.splytwebhook.service.ParameterComService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
public class ParameterComServiceHystrix implements ParameterComService {

    @Override
    public ResponseEntity<ApiCallResult> getCompany(String center, String id) {
        return null;
    }

    @Override
    public Company getOneCompany(String center, String id) {
        return null;
    }
}
