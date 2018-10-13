package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @param action
     * @param companyIdStr
     * @param userFullName
     * @param insuranceNumber
     * @param placeOfRegister
     * @param sortType
     * @param currentPageStr
     * @param session
     * @return
     */
    @GetMapping("/listInsurance")
    public ModelAndView listInsurance(@RequestParam(defaultValue = "") String action,
                                      @RequestParam(value = "company_id", defaultValue = "0") String companyIdStr,
                                      @RequestParam(value = "user_full_name", defaultValue = "") String userFullName,
                                      @RequestParam(value = "insurance_number", defaultValue = "") String insuranceNumber,
                                      @RequestParam(value = "place_of_register", defaultValue = "") String placeOfRegister,
                                      @RequestParam(value = "sort_type", defaultValue = "ASC") String sortType,
                                      @RequestParam(value = "current_page", defaultValue = "1") String currentPageStr,
                                      HttpSession session) {
        List<Company> companyList = companyService.findAllCompany();
        ModelAndView modelAndView = new ModelAndView("list_insurance");
        int companyIdSelected = Integer.parseInt(companyIdStr);
        int currentPage = Integer.parseInt(currentPageStr);
        if (action.isEmpty()) {
            companyIdSelected = companyList.get(0).getCompanyInternalId();
        }
        if (action.equals("sort") || action.equals("paging") || action.equals("back")) {
            companyIdSelected = (int) session.getAttribute("company_id_selected");
            userFullName = (String) session.getAttribute("user_full_name");
            insuranceNumber = (String) session.getAttribute("insurance_number");
            placeOfRegister = (String) session.getAttribute("place_of_register");
            if (action.equals("back")) {
                currentPage = (int) session.getAttribute("current_page");
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
            List<User> userList = userService.searchUser(companyIdSelected, userFullName, insuranceNumber,
                    placeOfRegister, sortType, Constant.LIMIT_USER, offset);
            session.setAttribute("company_id_selected", companyIdSelected);
            session.setAttribute("user_full_name", userFullName);
            session.setAttribute("insurance_number", insuranceNumber);
            session.setAttribute("place_of_register", placeOfRegister);
            session.setAttribute("sort_type", sortType);
            session.setAttribute("current_page", currentPage);

            modelAndView.addObject("user_list", userList);
            modelAndView.addObject("sort_type", sortType);
            modelAndView.addObject("page_list", pageList);
            modelAndView.addObject("previous_page", previousPage);
            modelAndView.addObject("current_page", currentPage);
            modelAndView.addObject("next_page", nextPage);
            modelAndView.addObject("total_page", totalPage);
        }
        modelAndView.addObject("company_list", companyList);
        modelAndView.addObject("company_id_selected", companyIdSelected);
        modelAndView.addObject("user_full_name", userFullName);
        modelAndView.addObject("insurance_number", insuranceNumber);
        modelAndView.addObject("place_of_register", placeOfRegister);
        modelAndView.addObject("total_user", totalUser);
        return modelAndView;
    }
}