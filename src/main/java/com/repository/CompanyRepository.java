package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    /**
     * Find all company with order company name ASC
     *
     * @return list of company
     */
    @Query(value = "SELECT * FROM tbl_company c ORDER BY c.company_name ASC", nativeQuery = true)
    List<Company> findAllCompany();

    /**
     * Find a company by id
     *
     * @return an object company
     */
    @Query(value = "SELECT * FROM tbl_company c WHERE c.company_internal_id = ?1", nativeQuery = true)
    Company findCompanyById(int companyId);

    /**
     * Find all company by company name
     *
     * @return an object company
     */
    @Query(value = "SELECT * FROM tbl_company c WHERE c.company_name = ?1", nativeQuery = true)
    Company findCompanyByName(String companyName);
}
