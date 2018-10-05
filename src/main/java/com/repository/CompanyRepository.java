package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
	
}
