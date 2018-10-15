package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.model.SearchInsuranceRequest;
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
    private UserService userService;


    /**
     * Get view of list insurance with method get
     *
     * @param action                 action of url
     * @param companyIdSelectedStr   company id is selected by user
     * @param searchInsuranceRequest object contains data of form
     * @param sortType               sort type is selected by user
     * @param currentPageString         current page is selected by the user
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
        List<Company> companyList = companyService.findAllCompany();
        ModelAndView modelAndView = new ModelAndView(Constant.VIEW_LIST_INSURANCE);
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
        }
        int totalUser = userService.countTotalUser(companyIdSelected, userFullName, insuranceNumber,
                placeOfRegister);
        if (totalUser > 0) {
            List<Integer> pageList = Common.getListPaging(totalUser, currentPage, Constant.LIMIT_PAGE);
            int offset = Common.getOffset(currentPage);
            int totalPage = Common.getTotalPage(totalUser, Constant.LIMIT_PAGE);
            int previousPage = Common.getPreviousPage(currentPage);
            int nextPage = Common.getNextPage(currentPage, totalPage);
            List<User> userList = userService.findUser(companyIdSelected, userFullName, insuranceNumber,
                    placeOfRegister, sortType, Constant.LIMIT_USER, offset);
            session.setAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, companyIdSelected);
            session.setAttribute(Constant.ATTRIBUTE_SORT_TYPE, sortType);
            session.setAttribute(Constant.ATTRIBUTE_CURRENT_PAGE, currentPage);

            modelAndView.addObject(Constant.ATTRIBUTE_USER_LIST, userList);
            modelAndView.addObject(Constant.ATTRIBUTE_SORT_TYPE, sortType);
            modelAndView.addObject(Constant.ATTRIBUTE_PAGE_LIST, pageList);
            modelAndView.addObject(Constant.ATTRIBUTE_PREVIOUS_PAGE, previousPage);
            modelAndView.addObject(Constant.ATTRIBUTE_CURRENT_PAGE, currentPage);
            modelAndView.addObject(Constant.ATTRIBUTE_NEXT_PAGE, nextPage);
            modelAndView.addObject(Constant.ATTRIBUTE_TOTAL_PAGE, totalPage);
        }
        modelAndView.addObject(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        modelAndView.addObject(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, companyIdSelected);
        modelAndView.addObject(Constant.ATTRIBUTE_TOTAL_USER, totalUser);
        return modelAndView;
    }
}
