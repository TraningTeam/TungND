package com.controller;

import com.model.Company;
import com.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AddUserController {
	
	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping("/addUser")
	public ModelAndView addUser() {
		List<Company> companyList = companyService.findAllCompany();
		ModelAndView modelAndView = new ModelAndView("add_user");
		modelAndView.addObject("company_list", companyList);
		return modelAndView;
	}
	
	@GetMapping("/getCompanyById/{id}")
	@ResponseBody
	public Company addUser(@PathVariable(value = "id") int companyId) {
		return companyService.findCompanyById(companyId);
	}
	
	@PostMapping("/addUser")
	public ModelAndView calculator() {
		return null;
	}
}
