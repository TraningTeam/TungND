package com.service;

import com.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CompanyService {

    /**
     * Find all company
     *
     * @return list company
     */
    List<Company> findAllCompany();

    /**
     * Find company by id
     *
     * @param companyId company id
     * @return an object company
     */
    Company findCompanyById(int companyId);

    /**
     * Check company name existed or not
     *
     * @param companyName company name
     * @return true if company name not exist and false if company name existed
     */
    boolean checkExistCompanyByCompanyName(String companyName);
}
