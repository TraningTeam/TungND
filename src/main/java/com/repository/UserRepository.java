package com.repository;

import java.util.List;

import com.model.User;

public interface UserRepository {
	
	List<User> searchUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
			String sortType, int limit, int offset);
	
	int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister);
	
	List<User> findUserDataToExport(int companyInternalId, String userFullName, String insuranceNumber,
			String placeOfRegister);
	
	boolean checkExistUserByUserName(String userName);
	
	void saveUser(User user);
}
