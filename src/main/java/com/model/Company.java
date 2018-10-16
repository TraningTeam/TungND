package com.model;


import com.util.Common;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_company")
public class Company {

    @Id
    @GeneratedValue()
    @Column(name = "company_internal_id")
    private int companyInternalId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    public Company() {
    }

    public Company(RegisterInsuranceRequest registerInsuranceRequest, Common common) {
        this.companyName = registerInsuranceRequest.getCompanyName();
        this.address = registerInsuranceRequest.getCompanyAddress();
        if (common.checkStringEmptyOrNull(registerInsuranceRequest.getCompanyEmail()) == true) {
            this.email = registerInsuranceRequest.getCompanyEmail();
        }
        if (common.checkStringEmptyOrNull(registerInsuranceRequest.getCompanyTelephone()) == true) {
            this.telephone = registerInsuranceRequest.getCompanyTelephone();
        }
    }

    public int getCompanyInternalId() {
        return companyInternalId;
    }

    public void setCompanyInternalId(int companyInternalId) {
        this.companyInternalId = companyInternalId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
