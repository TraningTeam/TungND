package com.service.imp;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.model.Company;
import com.model.Insurance;
import com.model.User;
import com.model.UserRegisterForm;
import com.repository.CompanyRepository;
import com.repository.InsuranceRepository;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private InsuranceRepository insuranceRepo;
	
	
	@Override
	public List<User> searchUser(int companyInternalId, String userFullName, String insuranceNumber,
			String placeOfRegister, String softType, int limit, int offset) {
		return userRepo
			.searchUser(companyInternalId, userFullName, insuranceNumber, placeOfRegister, softType, limit, offset);
	}
	
	@Override
	public int getTotalUser(int companyInternalId, String userFullName, String insuranceNumber,
			String placeOfRegister) {
		return userRepo
			.getTotalUser(companyInternalId, userFullName, insuranceNumber, placeOfRegister);
	}
	
	@Override
	public List<User> getUserDataToExport(int companyIdSelected, String userFullName, String insuranceNumber,
			String placeOfRegister) {
		return userRepo
			.getUserDataToExport(companyIdSelected, userFullName, insuranceNumber, placeOfRegister);
	}
	
	@Override
	public boolean checkExistUserByUserName(String userName) {
		return userRepo.checkExistUserByUserName(userName);
	}
	
	@Override
	@Transactional
	public void addUser(UserRegisterForm userRegisterForm) throws ParseException, RuntimeException {
		User user = new User(userRegisterForm);
		int companyId = 0;
		if (userRegisterForm.getCompanyFlag().equals("1")) {
			companyId = Integer.parseInt(userRegisterForm.getCompanyInternalId());
			user.setCompany(companyRepo.findCompanyById(companyId));
		} else {
			Company company = new Company(userRegisterForm);
			companyRepo.save(company);
			user.setCompany(company);
		}
		Insurance insurance = new Insurance(userRegisterForm);
		insuranceRepo.save(insurance);
		user.setInsurance(insurance);
		userRepo.saveUser(user);
	}
}
