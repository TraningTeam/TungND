package com.validate;

import java.text.ParseException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.model.UserRegisterForm;
import com.service.CompanyService;
import com.service.InsuranceService;
import com.service.UserService;
import com.util.Common;

@Component
public class UserValidation {
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	
	public boolean validate(UserRegisterForm userRegisterForm, Errors errors) throws ParseException {
		String insuranceNumber = userRegisterForm.getInsuranceNumber();
		String userName = userRegisterForm.getUserName();
		String password = userRegisterForm.getPassword();
		String userFullName = userRegisterForm.getUserFullName();
		String birthDate = userRegisterForm.getBirthDate();
		String companyFlag = userRegisterForm.getCompanyFlag();
		String companyName = userRegisterForm.getCompanyName();
		String companyAddress = userRegisterForm.getCompanyAddress();
		String companyEmail = userRegisterForm.getCompanyEmail();
		String companyTelephone = userRegisterForm.getCompanyTelephone();
		String placeOfRegister = userRegisterForm.getPlaceOfRegister();
		String insuranceStartDate = userRegisterForm.getInsuranceStartDate();
		String insuranceEndDate = userRegisterForm.getInsuranceEndDate();
		if (Common.checkStringEmptyOrNull(insuranceNumber) == false) {
			errors.rejectValue("insuranceNumber", "message.error.insurancenumber.01");
		} else if (Common.regexInsuranceNumber(insuranceNumber) == false) {
			errors.rejectValue("insuranceNumber", "message.error.insurancenumber.02");
		} else if (insuranceService.checkExistInsuranceNumber(insuranceNumber) == false) {
			errors.rejectValue("insuranceNumber", "message.error.insurancenumber.03");
		}
		if (Common.checkStringEmptyOrNull(userFullName) == false) {
			errors.rejectValue("userFullName", "message.error.userfullname.01");
		} else if (Common.checkMaxLength(userFullName) == false) {
			errors.rejectValue("userFullName", "message.error.userfullname.02");
		}
		if (Common.checkStringEmptyOrNull(userName) == false) {
			errors.rejectValue("userName", "message.error.username.01");
		} else if (Common.checkRangeLength(userName) == false) {
			errors.rejectValue("userName", "message.error.username.02");
		} else if (userService.checkExistUserByUserName(userName) == false) {
			errors.rejectValue("userName", "message.error.username.03");
		}
		if (Common.checkStringEmptyOrNull(password) == false) {
			errors.rejectValue("userName", "message.error.password.01");
		} else if (Common.regexPassword(password) == false) {
			errors.rejectValue("userName", "message.error.password.02");
		}
		if (Common.checkStringEmptyOrNull(birthDate) == true && Common.checkFormatDate(birthDate) == true) {
			if (Common.checkValidBirthdate(birthDate, Calendar.getInstance().getTime())) {
				errors.rejectValue("birthDate", "message.error.birthdate.01");
			}
		}
		if (companyFlag.equals("2") == true) {
			if (Common.checkStringEmptyOrNull(companyName) == false) {
				errors.rejectValue("companyName", "message.error.companyname.01");
			} else if (companyService.checkExistCompanyByCompanyName(companyName) == false) {
				errors.rejectValue("companyName", "message.error.companyname.02");
			}
			if (Common.checkStringEmptyOrNull(companyAddress) == false) {
				errors.rejectValue("companyAddress", "message.error.companyaddress.01");
			}
			if (Common.checkStringEmptyOrNull(companyEmail) == true && Common.regexEmail(companyEmail) == false) {
				errors.rejectValue("companyEmail", "message.error.companyemail.01");
			}
			if (Common.checkStringEmptyOrNull(companyTelephone) == true && Common.regexTelephone(companyTelephone) == false) {
				errors.rejectValue("companyTelephone", "message.error.companytelephone.01");
			}
		}
		if (Common.checkStringEmptyOrNull(placeOfRegister) == false) {
			errors.rejectValue("placeOfRegister", "message.error.placeofregister.01");
		}
		if (Common.checkStringEmptyOrNull(insuranceStartDate) == false) {
			errors.rejectValue("insuranceStartDate", "message.error.insurancestartdate.01");
		} else if (Common.checkFormatDate(insuranceStartDate) == false) {
			errors.rejectValue("insuranceStartDate", "message.error.insurancestartdate.02");
		} else if (Common.checkStringEmptyOrNull(insuranceEndDate) == false) {
			errors.rejectValue("insuranceEndDate", "message.error.insuranceenddate.01");
		} else if (Common.checkFormatDate(insuranceEndDate) == false) {
			errors.rejectValue("insuranceEndDate", "message.error.insuranceenddate.02");
		} else if (Common.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate) == false) {
			errors.rejectValue("insuranceEndDate", "message.error.insuranceenddate.03");
		}
		if (errors.hasErrors() == false) {
			return true;
		}
		return false;
	}
}
