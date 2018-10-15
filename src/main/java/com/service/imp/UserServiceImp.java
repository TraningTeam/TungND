package com.service.imp;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.Common;
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
        int companyId;
        if (registerInsuranceRequest.getCompanyFlag() == true) {
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

    @Override
    public Map<String, Object> getUserMapData(String userFullName, String insuranceNumber, String placeOfRegister, String sortType, int companyIdSelected, int totalUser, int currentPage) {
        List<Integer> pageList = Common.getListPaging(totalUser, currentPage, Constant.LIMIT_PAGE);
        int offset = Common.getOffset(currentPage);
        int totalPage = Common.getTotalPage(totalUser, Constant.LIMIT_PAGE);
        int previousPage = Common.getPreviousPage(currentPage);
        int nextPage = Common.getNextPage(currentPage, totalPage);
        List<User> userList = findUser(companyIdSelected, userFullName, insuranceNumber,
                placeOfRegister, sortType, Constant.LIMIT_USER, offset);
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(Constant.ATTRIBUTE_CURRENT_PAGE, currentPage);
        userMap.put(Constant.ATTRIBUTE_NEXT_PAGE, nextPage);
        userMap.put(Constant.ATTRIBUTE_PAGE_LIST, pageList);
        userMap.put(Constant.ATTRIBUTE_PREVIOUS_PAGE, previousPage);
        userMap.put(Constant.ATTRIBUTE_TOTAL_PAGE, totalPage);
        userMap.put(Constant.ATTRIBUTE_USER_LIST, userList);
        return userMap;
    }
}
