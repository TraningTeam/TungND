package com.service;

import com.model.SearchInsuranceRequest;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface InsuranceService {

    /**
     * Check insurance number exist or not
     *
     * @param insuranceNumber insurance number
     * @return true if insurance number not exist and false if insurance number existed
     */
    boolean checkExistInsuranceNumber(String insuranceNumber);

    /**
     * Get insurance map data
     *
     * @param action                 action of url
     * @param companyIdSelectedStr   company id is selected by user
     * @param searchInsuranceRequest object contains data of form
     * @param sortType               sort type is selected by user
     * @param currentPageString      current page is selected by the user
     * @param session                object to save data submit
     * @return a map insurance data
     */
    Map<String, Object> getInsuranceMapData(String action, String companyIdSelectedStr, SearchInsuranceRequest searchInsuranceRequest, String sortType, String currentPageString, HttpSession session);
}
