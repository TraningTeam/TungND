package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
	
	List<User> searchUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
			String sortType, int limit, int offset);
	
	int getTotalUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister);

    List<User> getUserDataToExport(int companyIdSelected, String userFullName, String insuranceNumber, String placeOfRegister);
}
