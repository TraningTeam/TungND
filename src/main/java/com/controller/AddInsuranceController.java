package com.controller;

import java.text.ParseException;
import java.util.List;

import com.service.UserService;
import com.util.Common;
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

import com.model.Company;
import com.model.UserRegisterForm;
import com.service.CompanyService;
import com.validate.UserValidation;

@Controller
public class AddInsuranceController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserValidation userValidation;

    @Autowired
    private UserService userService;


    @GetMapping("/addInsurance")
    public ModelAndView addInsurance(@ModelAttribute UserRegisterForm userRegisterForm) {
        initRegisterForm(userRegisterForm);
        List<Company> companyList = companyService.findAllCompany();
        ModelAndView modelAndView = new ModelAndView("add_insurance");
        modelAndView.addObject("company_list", companyList);
        modelAndView.addObject("company", companyList.get(0));
        return modelAndView;
    }


    @PostMapping("/addInsurance")
    public ModelAndView addInsurance(@ModelAttribute UserRegisterForm userRegisterForm,
                                     Errors errors) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("add_insurance");
        if (userValidation.validate(userRegisterForm, errors) == false) {
            List<Company> companyList = companyService.findAllCompany();
            int companyId = Integer.parseInt(userRegisterForm.getCompanyInternalId());
            Company company = companyService.findCompanyById(companyId);
            modelAndView.addObject("company_list", companyList);
            modelAndView.addObject("company", company);
            return modelAndView;
        } else {
            userService.addInsurance(userRegisterForm);
            return new ModelAndView("redirect:/listUser");
        }
    }

    @GetMapping("/getCompanyById/{id}")
    @ResponseBody
    public Company addInsurance(@PathVariable(value = "id") int companyId) {
        return companyService.findCompanyById(companyId);
    }

    @RequestMapping(value = "/formatName", method = RequestMethod.GET)
    @ResponseBody
    public String formatName(@RequestParam(value = "name", defaultValue = "") String name) {
        return Common.formatText(name);
    }

    private void initRegisterForm(UserRegisterForm userRegisterForm) {
        userRegisterForm.setInsuranceNumber("");
        userRegisterForm.setUserName("");
        userRegisterForm.setPassword("");
        userRegisterForm.setRePassword("");
        userRegisterForm.setUserFullName("");
        userRegisterForm.setUserSexDivision("1");
        userRegisterForm.setBirthDate("");
        userRegisterForm.setCompanyFlag("1");
        userRegisterForm.setCompanyName("");
        userRegisterForm.setCompanyAddress("");
        userRegisterForm.setCompanyEmail("");
        userRegisterForm.setCompanyTelephone("");
        userRegisterForm.setPlaceOfRegister("");
        userRegisterForm.setInsuranceStartDate("");
        userRegisterForm.setInsuranceEndDate("");
    }
}
