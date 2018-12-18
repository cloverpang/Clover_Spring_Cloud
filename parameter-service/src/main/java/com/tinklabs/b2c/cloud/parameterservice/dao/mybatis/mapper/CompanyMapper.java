package com.tinklabs.b2c.cloud.parameterservice.dao.mybatis.mapper;

import com.tinklabs.b2c.cloud.parameterservice.entity.Company;
import com.tinklabs.b2c.cloud.parameterservice.entity.entensions.CompanyExtension;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface CompanyMapper {
    public Company get(String id);

    public Company getByCode(Map<String, Object> param);

    public void insert(Company Company);

    public void update(Company Company);

    public void delete(String id);

    public List<Company> search(Map<String, Object> param);

    public int count(Map<String, Object> param);

    public List<CompanyExtension> searchFull(Map<String, Object> param);
}
