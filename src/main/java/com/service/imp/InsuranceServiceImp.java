package com.service.imp;

import com.model.Company;
import com.model.SearchInsuranceRequest;
import com.model.User;
import com.service.CompanyService;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.InsuranceRepository;
import com.service.InsuranceService;
import org.springframework.web.bind.annotation.ModelAttribute;

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

    @Override
    public boolean checkExistInsuranceNumber(String insuranceNumber) {
        if (insuranceRepo.findInsuranceByInsuranceNumber(insuranceNumber) == null) {
            return true;
        }
        return false;
    }

    @Override
    public Map<String, Object> getInsuranceMapData(String action, String companyIdSelectedStr, SearchInsuranceRequest searchInsuranceRequest, String sortType, String currentPageString, HttpSession session) {
        Map<String, Object> insuranceMap = new HashMap<>();
        List<Company> companyList = companyService.findAllCompany();
        int companyIdSelected = Integer.parseInt(companyIdSelectedStr);
        String userFullName = searchInsuranceRequest.getUserFullName();
        String insuranceNumber = searchInsuranceRequest.getInsuranceNumber();
        String placeOfRegister = searchInsuranceRequest.getPlaceOfRegister();
        int currentPage = Integer.parseInt(currentPageString);
        if (action.isEmpty() == true) {
            companyIdSelected = companyList.get(0).getCompanyInternalId();
        }
        if (action.equals(Constant.ACTION_SEARCH_TYPE.SORT.toString().toLowerCase()) == true
                || action.equals(Constant.ACTION_SEARCH_TYPE.PAGING.toString().toLowerCase()) == true
                || action.equals(Constant.ACTION_SEARCH_TYPE.BACK.toString().toLowerCase()) == true) {
            companyIdSelected = (int) session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED);
            userFullName = (String) session.getAttribute(Constant.ATTRIBUTE_USER_FULL_NAME);
            insuranceNumber = (String) session.getAttribute(Constant.ATTRIBUTE_INSURANCE_NUMBER);
            placeOfRegister = (String) session.getAttribute(Constant.ATTRIBUTE_PLACE_OF_REGISTER);
            if (action.equals(Constant.ACTION_SEARCH_TYPE.BACK.toString().toLowerCase()) == true) {
                currentPage = (int) session.getAttribute(Constant.ATTRIBUTE_CURRENT_PAGE);
            }
            if (action.equals(Constant.ACTION_SEARCH_TYPE.PAGING.toString().toLowerCase()) == true) {
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
