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
}
