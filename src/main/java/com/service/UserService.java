package com.service;

import java.text.ParseException;
import java.util.List;

import com.model.User;
import com.model.UserRegisterForm;

public interface UserService {

    List<User> searchUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
                          String sortType, int limit, int offset);

    int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister);

    List<User> findUserDataToExport(int companyIdSelected, String userFullName, String insuranceNumber, String placeOfRegister);

    boolean checkExistUserByUserName(String userName);

    void addInsurance(UserRegisterForm userRegisterForm) throws ParseException;
}
