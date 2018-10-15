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

    @Override
    public List<Company> findAllCompany() {
        return companyRepo.findAllCompany();
    }

    @Override
    public Company findCompanyById(int companyId) {
        return companyRepo.findCompanyById(companyId);
    }

    @Override
    public boolean checkExistCompanyByCompanyName(String companyName) {
        if (companyRepo.findCompanyByName(companyName) == null) {
            return true;
        }
        return false;
    }
}
