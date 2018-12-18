package com.tinklabs.b2c.cloud.splytwebhook.controller;

import com.tinklabs.b2c.cloud.splytwebhook.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.splytwebhook.entity.Company;
import com.tinklabs.b2c.cloud.splytwebhook.service.CacheComService;
import com.tinklabs.b2c.cloud.splytwebhook.service.ParameterComService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    CacheComService cacheComService;

    @Autowired
    ParameterComService parameterComService;

    /**
     * 获取所有服务
     */
    @RequestMapping("/cache-service")
    public Object services() {
        return discoveryClient.getInstances("b2c-cache-service");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @RequestMapping("/cache-service-discover")
    public Object discover() {
        return loadBalancer.choose("b2c-cache-service").getUri().toString();
    }

    //get a company for testing
    @RequestMapping(value={"/test-company"}, method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ApiCallResult> testGetCompany(@RequestParam(value = "id",required = true) String id) {
        ResponseEntity<ApiCallResult> apiCallResultResponseEntity;
        ApiCallResult result = new ApiCallResult();
        try{
            apiCallResultResponseEntity = parameterComService.getCompany("kexing",id);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("get company by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return apiCallResultResponseEntity;
    }

    @RequestMapping(value={"/test-getcompany"}, method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ApiCallResult> testGetOneCompany(@RequestParam(value = "id",required = true) String id) {
        Company company = new Company();
        ApiCallResult result = new ApiCallResult();

        try{
            company = parameterComService.getOneCompany("kexing",id);
            result.setMessage("get the company from parameter service!");
            result.setContent(company);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            result.setMessage("get one company by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
