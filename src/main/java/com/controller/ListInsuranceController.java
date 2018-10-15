package com.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.model.Insurance;
import com.model.SearchInsuranceRequest;
import com.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model.Company;
import com.model.User;
import com.service.CompanyService;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;

@Controller
public class ListInsuranceController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private InsuranceService insuranceService;


    /**
     * Get view of list insurance with method get
     *
     * @param action                 action of url
     * @param companyIdSelectedStr   company id is selected by user
     * @param searchInsuranceRequest object contains data of form
     * @param sortType               sort type is selected by user
     * @param currentPageString      current page is selected by the user
     * @param session                object to save data submit
     * @return view of list insurance
     */
    @GetMapping(Constant.URL_LIST_INSURANCE)
    public ModelAndView listInsurance(@RequestParam(defaultValue = "") String action,
                                      @RequestParam(value = Constant.ATTRIBUTE_COMPANY_ID_SELECTED, defaultValue = "1") String companyIdSelectedStr,
                                      @ModelAttribute SearchInsuranceRequest searchInsuranceRequest,
                                      @RequestParam(value = Constant.ATTRIBUTE_SORT_TYPE, defaultValue = "ASC") String sortType,
                                      @RequestParam(value = Constant.ATTRIBUTE_CURRENT_PAGE, defaultValue = "1") String currentPageString,
                                      HttpSession session) {
        ModelAndView modelAndView = new ModelAndView(Constant.VIEW_LIST_INSURANCE);
        Map<String, Object> insuranceMap = insuranceService.getInsuranceMapData(action, companyIdSelectedStr, searchInsuranceRequest, sortType, currentPageString, session);
        Map<String, Object> userMap = (Map<String, Object>) insuranceMap.get(Constant.ATTRIBUTE_USER_MAP);
        if (userMap != null) {
            modelAndView.addObject(Constant.ATTRIBUTE_CURRENT_PAGE, userMap.get(Constant.ATTRIBUTE_CURRENT_PAGE));
            modelAndView.addObject(Constant.ATTRIBUTE_NEXT_PAGE, userMap.get(Constant.ATTRIBUTE_NEXT_PAGE));
            modelAndView.addObject(Constant.ATTRIBUTE_PAGE_LIST, userMap.get(Constant.ATTRIBUTE_PAGE_LIST));
            modelAndView.addObject(Constant.ATTRIBUTE_PREVIOUS_PAGE, userMap.get(Constant.ATTRIBUTE_PREVIOUS_PAGE));
            modelAndView.addObject(Constant.ATTRIBUTE_TOTAL_PAGE, userMap.get(Constant.ATTRIBUTE_TOTAL_PAGE));
            modelAndView.addObject(Constant.ATTRIBUTE_USER_LIST, userMap.get(Constant.ATTRIBUTE_USER_LIST));
        }
        modelAndView.addObject(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, insuranceMap.get(Constant.ATTRIBUTE_COMPANY_ID_SELECTED));
        modelAndView.addObject(Constant.ATTRIBUTE_COMPANY_LIST, insuranceMap.get(Constant.ATTRIBUTE_COMPANY_LIST));
        modelAndView.addObject(Constant.ATTRIBUTE_SORT_TYPE, insuranceMap.get(Constant.ATTRIBUTE_SORT_TYPE));
        modelAndView.addObject(Constant.ATTRIBUTE_TOTAL_USER, insuranceMap.get(Constant.ATTRIBUTE_TOTAL_USER));
        return modelAndView;
    }
}
