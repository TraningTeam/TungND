package com.service.imp;

import com.model.Company;
import com.service.CompanyService;
import com.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {

    @Autowired
    CompanyRepository companyRepo;

    /**
     * Find all company
     *
     * @return list company
     */
    @Override
    public List<Company> findAllCompany() {
        return companyRepo.findAllCompany();
    }

    /**
     * Find company by id
     *
     * @param companyId company id
     * @return an object company
     */
    @Override
    public Company findCompanyById(int companyId) {
        return companyRepo.findCompanyById(companyId);
    }

    /**
     * Check company name existed or not
     *
     * @param companyName company name
     * @return true if company name not exist and false if company name existed
     */
    @Override
    public boolean checkExistCompanyByCompanyName(String companyName) {
        if (companyRepo.findCompanyByName(companyName) == null) {
            return true;
        }
        return false;
    }
}
