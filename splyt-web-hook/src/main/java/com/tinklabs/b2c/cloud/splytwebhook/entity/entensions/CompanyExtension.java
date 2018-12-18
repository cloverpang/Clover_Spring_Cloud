package com.tinklabs.b2c.cloud.splytwebhook.entity.entensions;

import com.tinklabs.b2c.cloud.splytwebhook.entity.Company;

public class CompanyExtension extends Company {
    private Integer companyCustomerQuantity; //服务人数

    public Integer getCompanyCustomerQuantity() {
        return companyCustomerQuantity;
    }

    public void setCompanyCustomerQuantity(Integer companyCustomerQuantity) {
        this.companyCustomerQuantity = companyCustomerQuantity;
    }
}
