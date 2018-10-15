package com.service.imp;

import java.text.ParseException;
import java.util.List;

import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.model.Company;
import com.model.Insurance;
import com.model.User;
import com.model.RegisterInsuranceRequest;
import com.repository.CompanyRepository;
import com.repository.InsuranceRepository;
import com.repository.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private InsuranceRepository insuranceRepo;


    @Override
    public List<User> findUser(int companyInternalId, String userFullName, String insuranceNumber,
                               String placeOfRegister, String softType, int limit, int offset) {
        return userRepo
                .findUser(companyInternalId, userFullName, insuranceNumber, placeOfRegister, softType, limit, offset);
    }

    @Override
    public int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber,
                              String placeOfRegister) {
        return userRepo
                .countTotalUser(companyInternalId, userFullName, insuranceNumber, placeOfRegister);
    }

    @Override
    public List<User> findUserDataToExport(int companyIdSelected, String userFullName, String insuranceNumber,
                                           String placeOfRegister) {
        return userRepo
                .findUserDataToExport(companyIdSelected, userFullName, insuranceNumber, placeOfRegister);
    }

    @Override
    public boolean checkExistUserByUserName(String userName) {
        return userRepo.checkExistUserByUserName(userName);
    }

    @Override
    @Transactional
    public void saveInsurance(RegisterInsuranceRequest registerInsuranceRequest) throws ParseException, RuntimeException {
        User user = new User(registerInsuranceRequest);
        int companyId = 0;
        if (registerInsuranceRequest.getCompanyFlag().equals(Constant.FLAG_EXIST_COMPANY)) {
            companyId = Integer.parseInt(registerInsuranceRequest.getCompanyInternalId());
            user.setCompany(companyRepo.findCompanyById(companyId));
        } else {
            Company company = new Company(registerInsuranceRequest);
            companyRepo.save(company);
            user.setCompany(company);
        }
        Insurance insurance = new Insurance(registerInsuranceRequest);
        insuranceRepo.save(insurance);
        user.setInsurance(insurance);
        userRepo.saveUser(user);
    }
}
