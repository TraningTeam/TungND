package com.repository;

import java.util.List;

import com.model.User;


public interface UserRepository {

	List<User> searchUser(int companyInternalId, String userFullName, String insuranceNumber, String placeOfRegister,
			String sortType, int limit,int offset);
}
