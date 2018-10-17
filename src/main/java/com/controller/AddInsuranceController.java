package com.controller;

import com.model.Company;
import com.model.RegisterInsuranceRequest;
import com.service.CompanyService;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;
import com.validate.InsuranceValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
public class AddInsuranceController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private InsuranceValidation insuranceValidation;

    @Autowired
    private UserService userService;

    @Autowired
    private Common common;

    /**
     * Get view of add insurance with method get
     *
     * @param registerInsuranceRequest
     * @return view of add insurance
     */
    @GetMapping(Constant.URL_ADD_INSURANCE)
    public ModelAndView addInsurance(@ModelAttribute RegisterInsuranceRequest registerInsuranceRequest,
                                     HttpSession session) {
        List<Company> companyList = companyService.findAllCompany();
        Company company = companyService.findCompanyById((int) session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED));
        registerInsuranceRequest.setCompanyInternalId(String.valueOf(company.getCompanyInternalId()));
        ModelAndView modelAndView = new ModelAndView("add_insurance");
        modelAndView.addObject(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        modelAndView.addObject(Constant.ATTRIBUTE_COMPANY, company);
        return modelAndView;
    }

    /**
     * Get view of add insurance or get view of list insurance with method post
     *
     * @param registerInsuranceRequest form contains field to register
     * @param errors                   object contains all of error
     * @return redirect to url /listInsurance if <code> userValidation.validate(registerInsuranceRequest, errors) </code> is false
     * Or view of add insurance if if <code> userValidation.validate(registerInsuranceRequest, errors) </code> is true
     * @throws ParseException if date field into {@code registerInsuranceRequest} convert by {@date format} is invalid
     */
    @PostMapping(Constant.URL_ADD_INSURANCE)
    public ModelAndView addInsurance(@ModelAttribute RegisterInsuranceRequest registerInsuranceRequest,
                                     Errors errors) throws ParseException {
        ModelAndView modelAndView = new ModelAndView(Constant.VIEW_ADD_INSURANCE);
        if (insuranceValidation.validate(registerInsuranceRequest, errors) == false) {
            userService.saveUser(registerInsuranceRequest);
            return new ModelAndView("redirect:" + Constant.URL_LIST_INSURANCE);
        } else {
            List<Company> companyList = companyService.findAllCompany();
            int companyId = Integer.parseInt(registerInsuranceRequest.getCompanyInternalId());
            Company company = companyService.findCompanyById(companyId);
            modelAndView.addObject(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
            modelAndView.addObject(Constant.ATTRIBUTE_COMPANY, company);
            return modelAndView;
        }
    }

    /**
     * Find company info by id
     *
     * @param companyId company id is selected by user
     * @return object company found by id
     */
    @GetMapping("/findCompanyById/{id}")
    @ResponseBody
    public Company findCompanyById(@PathVariable(value = "id") int companyId) {
        return companyService.findCompanyById(companyId);
    }

    /**
     * Format a text
     *
     * @param name string to format
     * @return string is formatted
     */
    @RequestMapping(value = "/formatName", method = RequestMethod.GET)
    @ResponseBody
    public String formatName(@RequestParam(value = "name", defaultValue = "") String name) {
        return common.formatText(name);
    }

}
