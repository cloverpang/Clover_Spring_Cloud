package com.tinklabs.b2c.cloud.parameterservice.service;

import com.tinklabs.b2c.cloud.parameterservice.beans.PageBean;
import com.tinklabs.b2c.cloud.parameterservice.entity.Company;
import com.tinklabs.b2c.cloud.parameterservice.entity.entensions.CompanyExtension;

import java.util.List;

public interface CompanyService {
    Company get(String id) throws Exception;

    Company getByCode(String code, String center) throws Exception;

    Boolean insert(Company Company) throws Exception;

    Boolean update(Company Company) throws Exception;

    Boolean delete(String id) throws Exception;

    Boolean batchDelete(String ids) throws Exception;

    PageBean<Company> page(String conditionsStr, int pageSize, int pageNo,
                           String sortColumn, String sortType, String force) throws Exception;

    List<Company> search(String conditionsStr, int pageSize, int pageNo, String sortColumn, String sortType) throws Exception;

    PageBean<CompanyExtension> pageExtend(String conditionsStr, int pageSize, int pageNo,
                                          String sortColumn, String sortType) throws Exception;
}
