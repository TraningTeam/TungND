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
     * Find company by id
     *
     * @param companyName company name
     * @return an object company
     */
    boolean checkExistCompanyByCompanyName(String companyName);
}
