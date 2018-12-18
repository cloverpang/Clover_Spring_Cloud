package com.tinklabs.b2c.cloud.parameterservice.controller.company;

import com.tinklabs.b2c.cloud.parameterservice.annotation.PermssionSecured;
import com.tinklabs.b2c.cloud.parameterservice.annotation.TokenSecured;
import com.tinklabs.b2c.cloud.parameterservice.beans.ApiCallResult;
import com.tinklabs.b2c.cloud.parameterservice.beans.PageBean;
import com.tinklabs.b2c.cloud.parameterservice.controller.BaseController;
import com.tinklabs.b2c.cloud.parameterservice.entity.Company;
import com.tinklabs.b2c.cloud.parameterservice.entity.entensions.CompanyExtension;
import com.tinklabs.b2c.cloud.parameterservice.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SpringBootApplication
@RequestMapping(value={"/{center}"})
@Api(tags = {"company"}, description = "Company APIs")
public class CompanyController extends BaseController {

    protected Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @ApiOperation(value="get one Company ", notes="",response = Company.class)
    @RequestMapping(value={"/get/company/{id}"}, method= RequestMethod.GET)
    public Company getcompany(@PathVariable String center,@PathVariable("id") String id){
        logger.info("invoke: " + "/company/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Company company = companyService.get(id);

            return company;
        }catch (Exception e){
            result.setMessage("get company by id failed : " + e.getStackTrace());
            return null;
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_get_one")
    @ApiOperation(value="get Company ", notes="",response = Company.class)
    @RequestMapping(value={"/company/{id}"}, method= RequestMethod.GET)
    public ResponseEntity<ApiCallResult> get(@PathVariable String center, @PathVariable("id") String id){
        logger.info("invoke: " + "/company/" + id);
        ApiCallResult result = new ApiCallResult();
        try{
            Company company = companyService.get(id);
            if (null != company) {
                result.setContent(company);
            } else {
                result.setMessage("can not found out this company ");
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("get company by id failed : " + e.getStackTrace());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_get_list")
    @RequestMapping(value={"/companies"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search company API", response = Company.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> search(@PathVariable String center,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                @ApiParam(value = "column name")
                                                @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                @ApiParam(value = "desc or asc,default value is desc")
                                                @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr,
                                                @ApiParam(value = "force")
                                                @RequestParam(value = "force", required = false, defaultValue = "notForceData") String force){
        logger.info("invoke: " + "/companies");
        ApiCallResult result = new ApiCallResult();
        try{
            conditionsStr = conditionsStr + "$operationCenterCode::=::" + center;
            PageBean<Company> companyPageBean = companyService.page(conditionsStr, pageSize, pageNumber, sortColumn, sortType,force);
            result.setContent(companyPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_add")
    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ApiOperation(value = "Create company API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> createCompany(@PathVariable String center,
                                                       @RequestBody Company company) {
        logger.info("invoke: " + "/company/");
        ApiCallResult result = new ApiCallResult();
        try{
            company.setOperationCenterCode(center);
            Boolean excute = companyService.insert(company);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("create company failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_update")
    @RequestMapping(value = "/company", method = RequestMethod.PUT)
    @ApiOperation(value = "Save company API", response = Boolean.class)
    public ResponseEntity<ApiCallResult> saveCompany(@PathVariable String center,
                                                     @RequestBody Company company) {
        logger.info("invoke: " + "/company/");
        ApiCallResult result = new ApiCallResult();
        try{
            company.setOperationCenterCode(center);
            Boolean excute = companyService.update(company);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("save company failed : " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_delete")
    @RequestMapping(value = "company/{id}", method = RequestMethod.DELETE)
         @ApiOperation(value = "Delete company API", response = boolean.class)
         public ResponseEntity<ApiCallResult> deleteCompany(@PathVariable String center,
                                                            @ApiParam(value = "id", required = true)
            @PathVariable("id") String id) {
        logger.info("invoke: " + "/company/");
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = companyService.delete(id);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("delete company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_batch_delete")
    @RequestMapping(value = "companies/{ids}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete companies API", response = boolean.class)
    public ResponseEntity<ApiCallResult> batchDeleteCompany(@PathVariable String center,
                                                            @ApiParam(value = "ids", required = true)
            @PathVariable("ids") String ids) {
        logger.info("invoke: " + "/companies/" + ids);
        ApiCallResult result = new ApiCallResult();
        try{
            Boolean excute = companyService.batchDelete(ids);
            result.setContent(excute);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("batch delete companies failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @TokenSecured
    @PermssionSecured(value="company_get_list")
    @RequestMapping(value={"/companyExtends"}, method= RequestMethod.GET)
    @ApiOperation(value = "Search company API", response = Company.class,responseContainer =
            "List")
    public ResponseEntity<ApiCallResult> searchFull(@PathVariable String center,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
                                                    @RequestParam(value = "page", required = false, defaultValue = "1") int pageNumber,
                                                    @ApiParam(value = "column name")
                                                    @RequestParam(value = "sortColumn", required = false, defaultValue = "createTime") String sortColumn,
                                                    @ApiParam(value = "desc or asc,default value is desc")
                                                    @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                    @ApiParam(value = "like this - name::like::clover$type::=::AI")
                                                    @RequestParam(value = "conditionsStr", required = false, defaultValue = "") String conditionsStr){
        logger.info("invoke: " + "/companyExtends");
        ApiCallResult result = new ApiCallResult();
        try{
            conditionsStr = conditionsStr + "$operationCenterCode::=::" + center;
            PageBean<CompanyExtension> companyPageBean = companyService.pageExtend(conditionsStr, pageSize, pageNumber, sortColumn, sortType);
            result.setContent(companyPageBean);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            result.setMessage("search company failed : " + ExceptionUtils.getFullStackTrace(e));
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
