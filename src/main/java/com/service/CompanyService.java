package com.service;

import com.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {
	
	List<Company> findAllCompany();

    Company findCompanyById(int companyId);

    boolean checkExistCompanyByCompanyName(String companyName);
}
