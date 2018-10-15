package com.model;

import com.util.Common;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_internal_id")
    private int userInternalId;

    @ManyToOne
    @JoinColumn(name = "company_internal_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "insurance_internal_id")
    private Insurance insurance;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_full_name")
    private String userFullName;

    @Column(name = "user_sex_division")
    private char userSexDivision;

    @Column(name = "birthdate")
    private Date birthDate;


    public User() {
    }

    public User(RegisterInsuranceRequest registerInsuranceRequest) throws ParseException {
        this.userFullName = registerInsuranceRequest.getUserFullName();
        this.userName = registerInsuranceRequest.getUserName();
        this.password = registerInsuranceRequest.getPassword();
        this.userSexDivision = registerInsuranceRequest.getUserSexDivision().charAt(0);
        if (Common.checkStringEmptyOrNull(registerInsuranceRequest.getBirthDate()) == true) {
            this.birthDate = Common.convertStringToDate(registerInsuranceRequest.getBirthDate());
        }
    }

    public int getUserInternalId() {
        return userInternalId;
    }

    public void setUserInternalId(int userInternalId) {
        this.userInternalId = userInternalId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public char getUserSexDivision() {
        return userSexDivision;
    }

    public void setUserSexDivision(char userSexDivision) {
        this.userSexDivision = userSexDivision;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

}
