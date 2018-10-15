package com.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.model.User;
import com.model.RegisterInsuranceRequest;

public interface UserService {

    /**
     * Find all user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @param sortType          sort type of user full name
     * @param limit             limit of user found
     * @param offset            index of start user in database
     * @return list user
     */
    List<User> findUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
                        String sortType, int limit, int offset);

    /**
     * Count total user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return total user
     */
    int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister);

    /**
     * Find user with condition search to export
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return list user
     */
    List<User> findUserDataToExport(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister);

    /**
     * Check user name existed or not
     *
     * @param userName user name
     * @return true if user name not exist and false if user name existed
     */
    boolean checkExistUserByUserName(String userName);

    /**
     * Save insurance data
     *
     * @param registerInsuranceRequest object contains all data to register
     * @throws ParseException if convert a date invalid
     */
    void saveInsurance(RegisterInsuranceRequest registerInsuranceRequest) throws ParseException;

    /**
     * Get data user map
     *
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @param sortType          sort type
     * @param companyIdSelected company id is selected by user
     * @param totalUser         total number of user found
     * @param currentPage       current page is selected by user
     * @return a map processed
     */
    Map<String, Object> getUserMapData(String userFullName, String insuranceNumber, String placeOfRegister, String sortType, int companyIdSelected, int totalUser, int currentPage);
}
