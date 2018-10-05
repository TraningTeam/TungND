package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddUserController {
	
	@GetMapping("/addUser")
	public ModelAndView addUser() {
		return new ModelAndView("add_user");
	}
	
	@PostMapping("/addUser")
	public ModelAndView calculator() {
		return null;
	}
}
