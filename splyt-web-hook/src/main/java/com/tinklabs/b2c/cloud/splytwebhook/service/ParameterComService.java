package com.tinklabs.b2c.cloud.splytwebhook.service;

import com.tinklabs.b2c.cloud.splytwebhook.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.splytwebhook.config.FeignConfigger;
import com.tinklabs.b2c.cloud.splytwebhook.entity.Company;
import com.tinklabs.b2c.cloud.splytwebhook.service.hystrix.ParameterComServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "b2c-parameter-service",configuration = FeignConfigger.class,fallback = ParameterComServiceHystrix.class)
public interface ParameterComService {
    @GetMapping(value = "/{center}/company/{id}")
    public ResponseEntity<ApiCallResult> getCompany(@PathVariable("center") String center, @PathVariable("id") String id);

    @GetMapping(value = "/{center}/get/company/{id}")
    public Company getOneCompany(@PathVariable("center") String center, @PathVariable("id") String id);
}
