package com.service.imp;

import com.model.Company;
import com.model.SearchInsuranceRequest;
import com.repository.InsuranceRepository;
import com.service.CompanyService;
import com.service.InsuranceService;
import com.service.UserService;
import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InsuranceServiceImp implements InsuranceService {

    @Autowired
    private InsuranceRepository insuranceRepo;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    /**
     * Check insurance number exist or not
     *
     * @param insuranceNumber insurance number
     * @return true if insurance number not exist and false if insurance number existed
     */
    @Override
    public boolean checkExistInsuranceNumber(String insuranceNumber) {
        if (insuranceRepo.findInsuranceByInsuranceNumber(insuranceNumber) == null) {
            return true;
        }
        return false;
    }

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
    @Override
    public Map<String, Object> getInsuranceMapData(String action, String companyIdSelectedStr, SearchInsuranceRequest searchInsuranceRequest, String sortType, String currentPageString, HttpSession session) {
        Map<String, Object> insuranceMap = new HashMap<>();
        List<Company> companyList = companyService.findAllCompany();
        int companyIdSelected = Integer.parseInt(companyIdSelectedStr);
        String userFullName = searchInsuranceRequest.getUserFullName();
        String insuranceNumber = searchInsuranceRequest.getInsuranceNumber();
        String placeOfRegister = searchInsuranceRequest.getPlaceOfRegister();
        int currentPage = Integer.parseInt(currentPageString);
        if (action.isEmpty()) {
            companyIdSelected = companyList.get(0).getCompanyInternalId();
        }
        if (action.equals(Constant.ACTION_SEARCH_TYPE.SORT.toString().toLowerCase())
                || action.equals(Constant.ACTION_SEARCH_TYPE.PAGING.toString().toLowerCase())
                || action.equals(Constant.ACTION_SEARCH_TYPE.BACK.toString().toLowerCase())) {
            companyIdSelected = (int) session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED);
            userFullName = (String) session.getAttribute(Constant.ATTRIBUTE_USER_FULL_NAME);
            insuranceNumber = (String) session.getAttribute(Constant.ATTRIBUTE_INSURANCE_NUMBER);
            placeOfRegister = (String) session.getAttribute(Constant.ATTRIBUTE_PLACE_OF_REGISTER);
            if (action.equals(Constant.ACTION_SEARCH_TYPE.BACK.toString().toLowerCase())) {
                currentPage = (int) session.getAttribute(Constant.ATTRIBUTE_CURRENT_PAGE);
            }
            if (action.equals(Constant.ACTION_SEARCH_TYPE.PAGING.toString().toLowerCase())) {
                sortType = (String) session.getAttribute(Constant.ATTRIBUTE_SORT_TYPE);
            }
            searchInsuranceRequest.setUserFullName(userFullName);
            searchInsuranceRequest.setInsuranceNumber(insuranceNumber);
            searchInsuranceRequest.setPlaceOfRegister(placeOfRegister);
        }
        int totalUser = userService.countTotalUser(companyIdSelected, userFullName, insuranceNumber,
                placeOfRegister);
        if (totalUser > 0) {
            Map<String, Object> userMap = userService.getUserMapData(userFullName, insuranceNumber, placeOfRegister, sortType, companyIdSelected, totalUser, currentPage);
            insuranceMap.put(Constant.ATTRIBUTE_USER_MAP, userMap);
        }
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, companyIdSelected);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, sortType);
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, totalUser);
        session.setAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, companyIdSelected);
        session.setAttribute(Constant.ATTRIBUTE_CURRENT_PAGE, currentPage);
        session.setAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, companyIdSelected);
        session.setAttribute(Constant.ATTRIBUTE_INSURANCE_NUMBER, insuranceNumber);
        session.setAttribute(Constant.ATTRIBUTE_PLACE_OF_REGISTER, placeOfRegister);
        session.setAttribute(Constant.ATTRIBUTE_SORT_TYPE, sortType);
        session.setAttribute(Constant.ATTRIBUTE_USER_FULL_NAME, userFullName);
        return insuranceMap;
    }
}
