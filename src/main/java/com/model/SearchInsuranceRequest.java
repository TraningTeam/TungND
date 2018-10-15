package com.model;

public class SearchInsuranceRequest {

    private String companyId;
    private String userFullName;
    private String insuranceNumber;
    private String placeOfRegister;


    public SearchInsuranceRequest() {
        this.userFullName = "";
        this.insuranceNumber = "";
        this.placeOfRegister = "";
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getPlaceOfRegister() {
        return placeOfRegister;
    }

    public void setPlaceOfRegister(String placeOfRegister) {
        this.placeOfRegister = placeOfRegister;
    }

}
