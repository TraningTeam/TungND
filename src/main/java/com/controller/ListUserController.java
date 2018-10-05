package com.controller;

import com.model.Company;
import com.model.User;
import com.service.UserService;
import com.service.imp.CompanyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.service.CompanyService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ListUserController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/listUser")
	public ModelAndView listUser(@RequestParam(defaultValue = "") String action,
			@RequestParam(value = "company_id", defaultValue = "0") String companyIdStr,
			@RequestParam(value = "user_full_name", defaultValue = "") String userFullName,
			@RequestParam(value = "insurance_number", defaultValue = "") String insuranceNumber,
			@RequestParam(value = "place_of_register", defaultValue = "") String placeOfRegister) {
		List<Company> companyList = companyService.findAllCompany();
		List<User> userList = null;
		int companyId = Integer.parseInt(companyIdStr);
		if (action.isEmpty()) {
			companyId = companyList.get(0).getCompanyInternalId();
			userList = userService.searchUser(companyId, userFullName, insuranceNumber,
					placeOfRegister, "ASC", 5, 0);
		}
		if (action.equals("search")) {
			userList = userService.searchUser(companyId, userFullName, insuranceNumber,
					placeOfRegister, "ASC", 5, 0);
		}
		ModelAndView modelAndView = new ModelAndView("list_user");
		modelAndView.addObject("company_list", companyList);
		modelAndView.addObject("user_list", userList);
		modelAndView.addObject("user_full_name", userFullName);
		modelAndView.addObject("insurance_nmber", insuranceNumber);
		modelAndView.addObject("place_of_register", placeOfRegister);
		return modelAndView;
	}
}
