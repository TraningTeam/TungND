package com.model;

public class RegisterInsuranceRequest {

    private String insuranceNumber;

    private String userFullName;

    private String userName;

    private String password;

    private String rePassword;

    private String userSexDivision;

    private String birthDate;

    private boolean companyFlag;

    private String companyInternalId;

    private String companyName;

    private String companyAddress;

    private String companyEmail;

    private String companyTelephone;

    private String placeOfRegister;

    private String insuranceStartDate;

    private String insuranceEndDate;


    public RegisterInsuranceRequest() {
        this.insuranceNumber = "";
        this.userName = "";
        this.password = "";
        this.rePassword = "";
        this.userFullName = "";
        this.userSexDivision = "1";
        this.birthDate = "";
        this.companyFlag = true;
        this.companyName = "";
        this.companyAddress = "";
        this.companyEmail = "";
        this.companyTelephone = "";
        this.placeOfRegister = "";
        this.insuranceStartDate = "";
        this.insuranceEndDate = "";
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getUserSexDivision() {
        return userSexDivision;
    }

    public void setUserSexDivision(String userSexDivision) {
        this.userSexDivision = userSexDivision;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCompanyInternalId() {
        return companyInternalId;
    }

    public void setCompanyInternalId(String companyInternalId) {
        this.companyInternalId = companyInternalId;
    }

    public boolean getCompanyFlag() {
        return companyFlag;
    }

    public void setCompanyFlag(boolean companyFlag) {
        this.companyFlag = companyFlag;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyTelephone() {
        return companyTelephone;
    }

    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    public String getPlaceOfRegister() {
        return placeOfRegister;
    }

    public void setPlaceOfRegister(String placeOfRegister) {
        this.placeOfRegister = placeOfRegister;
    }

    public String getInsuranceStartDate() {
        return insuranceStartDate;
    }

    public void setInsuranceStartDate(String insuranceStartDate) {
        this.insuranceStartDate = insuranceStartDate;
    }

    public String getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(String insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }
}
