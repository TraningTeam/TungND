package com.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	
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
}
