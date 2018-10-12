package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
	
	@Query(value = "SELECT * FROM tbl_company c ORDER BY c.company_name ASC", nativeQuery = true)
	List<Company> findAllCompany();
	
	@Query(value = "SELECT * FROM tbl_company c WHERE c.company_internal_id = ?1", nativeQuery = true)
	Company findCompanyById(int companyId);
	
	@Query(value = "SELECT * FROM tbl_company c WHERE c.company_name = ?1", nativeQuery = true)
	Company findByCompanyName(String companyName);
}
