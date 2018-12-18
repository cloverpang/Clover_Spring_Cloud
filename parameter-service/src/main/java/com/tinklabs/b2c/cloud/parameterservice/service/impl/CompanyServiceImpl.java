package com.tinklabs.b2c.cloud.parameterservice.service.impl;

import com.tinklabs.b2c.cloud.parameterservice.annotation.QueryCache;
import com.tinklabs.b2c.cloud.parameterservice.beans.PageBean;
import com.tinklabs.b2c.cloud.parameterservice.consts.Consts;
import com.tinklabs.b2c.cloud.parameterservice.dao.mybatis.mapper.CompanyMapper;


import com.tinklabs.b2c.cloud.parameterservice.entity.Company;
import com.tinklabs.b2c.cloud.parameterservice.entity.entensions.CompanyExtension;
import com.tinklabs.b2c.cloud.parameterservice.service.BaseService;
import com.tinklabs.b2c.cloud.parameterservice.util.CriteriaMapUtils;
import com.tinklabs.b2c.cloud.parameterservice.util.DateUtils;
import com.tinklabs.b2c.cloud.parameterservice.util.IDUtils;
import com.tinklabs.b2c.cloud.parameterservice.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
@EnableTransactionManagement// 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven/>
public class CompanyServiceImpl extends BaseService implements com.tinklabs.b2c.cloud.parameterservice.service.CompanyService {
    protected Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    @Autowired
    private CompanyMapper companyMapper;

    @QueryCache
    @Override
    public Company get(String id) throws Exception {
        try{
            return companyMapper.get(id);
        }catch (Exception e){
            logger.error("failed on get company info : " + e.getMessage());
            throw new Exception("failed on get company : ",e);
        }
    }

    @Override
    public Company getByCode(String code,String center) throws Exception {
        try{
            Map<String, Object> criteriaMap = new HashMap<String, Object>();
            if(StringUtils.isNotEmpty(code)){
                criteriaMap.put("code", code);
            }
            if(StringUtils.isNotEmpty(center)){
                criteriaMap.put("center", center);
            }
            return companyMapper.getByCode(criteriaMap);
        }catch (Exception e){
            logger.error("failed on get company info : " + e.getMessage());
            throw new Exception("failed on get company : ",e);
        }
    }

    @Override
    public Boolean insert(Company company) throws Exception {
        try{
            if(StringUtils.isEmpty(company.getCompanyCode())){
                throw new Exception("company code cannot empty! ");
            }

            Company originCompany = getByCode(company.getCompanyCode(),company.getOperationCenterCode());
            if(null != originCompany){
                throw new Exception("This customer code is existing, please change an new company code! ");
            }

            //re-set the companyId
            company.setCompanyId(IDUtils.generateID());

            if(null == company.getJoinTime()){
                company.setJoinTime(DateUtils.now());
            }

            company.setCreateTime(DateUtils.now());

            companyMapper.insert(company);
            return true;
        }catch (Exception e){
            logger.error("failed on insert company : " + e.getMessage());
            throw new Exception(e);
        }
    }

    @Override
    public Boolean update(Company company) throws Exception {
        try{
            companyMapper.update(company);
            return true;
        }catch (Exception e){
            logger.error("failed on update company : " + e.getMessage());
            throw new Exception("failed on update company : ",e);
        }
    }

    @Override
    public Boolean delete(String id) throws Exception {
        try{
            Company originCompany = get(id);
            companyMapper.delete(originCompany.getCompanyId());
            return true;
        }catch (Exception e){
            logger.error("failed on delete company : " + e.getMessage());
            throw new Exception("failed on delete company : ",e);
        }
    }

    @Transactional
    @Override
    public Boolean batchDelete(String ids) throws Exception {
        try{
            if(StringUtils.isEmpty(ids)){
                throw new Exception("Ids can not empty!");
            }
            String[] idArr = ids.split(Consts.COMMA);
            for(String id : idArr){
                if(StringUtils.isNotEmpty(id)){
                    Company originCompany = get(id);
                    companyMapper.delete(originCompany.getCompanyId());
                }
            }
            return true;
        }catch (Exception e){
            logger.error("failed on delete company : " + e.getMessage());
            throw new Exception("failed on delete company : ",e);
        }
    }

    @Override
    @QueryCache
    public PageBean<Company> page(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType, String force) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);


            PageBean<Company> companyPageBean = new PageBean<Company>();
            int totalRecords = companyMapper.count(criteriaMap);

            companyPageBean.setTotalSize(totalRecords);
            companyPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            companyPageBean.setTotalPageNum(numOfPages);

            List<Company> items = companyMapper.search(criteriaMap);
            if(CollectionUtils.isEmpty(items)){
                items = Collections.emptyList();
            }

            companyPageBean.setItem(items);
            return companyPageBean;
        }catch (Exception e){
            throw new Exception("Search company exception : " + e.getMessage());
        }
    }



    @Override
    public List<Company> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("", "", conditionsStr,
                    pageSize, pageNo, sortColumn, sortType);

            return companyMapper.search(criteriaMap);
        }catch (Exception e){
            throw new Exception("Search company exception : " + e.getMessage());
        }
    }

    @Override
    public PageBean<CompanyExtension> pageExtend(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception {
        try{
            Map<String, Object> criteriaMap = CriteriaMapUtils.commonCriteriaMapGenerate("","",conditionsStr,
                    pageSize,pageNo,sortColumn,sortType);

            PageBean<CompanyExtension> companyPageBean = new PageBean<CompanyExtension>();
            int totalRecords = companyMapper.count(criteriaMap);

            companyPageBean.setTotalSize(totalRecords);
            companyPageBean.setPageNo(pageNo);
            int numOfPages = Double.valueOf(Math.ceil((1.0 * totalRecords) / pageSize)).intValue();
            companyPageBean.setTotalPageNum(numOfPages);

            List<CompanyExtension> items = companyMapper.searchFull(criteriaMap);
            if(CollectionUtils.isEmpty(items)){
                items = Collections.emptyList();
            }

            companyPageBean.setItem(items);
            return companyPageBean;
        }catch (Exception e){
            throw new Exception("Search company exception : " + e.getMessage());
        }
    }
}
