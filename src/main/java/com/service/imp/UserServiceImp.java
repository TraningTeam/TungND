package com.service.imp;

import com.model.Company;
import com.model.Insurance;
import com.model.RegisterInsuranceRequest;
import com.model.User;
import com.repository.CompanyRepository;
import com.repository.InsuranceRepository;
import com.repository.UserRepository;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private InsuranceRepository insuranceRepo;

    @Autowired
    private Common common;

    /**
     * Find all user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @param sortType          sort type of user full name
     * @param limit             limit of user found
     * @param offset            index of start user in database
     * @return list user
     */
    @Override
    public List<User> findUser(int companyInternalId, String userFullName, String insuranceNumber,
                               String placeOfRegister, String sortType, int limit, int offset) {
        return userRepo
                .findUser(companyInternalId, userFullName, insuranceNumber, placeOfRegister, sortType, limit, offset);
    }

    /**
     * Count total user with condition search
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return total user
     */
    @Override
    public int countTotalUser(int companyInternalId, String userFullName, String insuranceNumber,
                              String placeOfRegister) {
        return userRepo
                .countTotalUser(companyInternalId, userFullName, insuranceNumber, placeOfRegister);
    }

    /**
     * Find user with condition search to export
     *
     * @param companyInternalId company id
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @return list user
     */
    @Override
    public List<User> findUserDataToExport(int companyInternalId, String userFullName, String insuranceNumber,
                                           String placeOfRegister) {
        return userRepo
                .findUserDataToExport(companyInternalId, userFullName, insuranceNumber, placeOfRegister);
    }

    /**
     * Check user name existed or not
     *
     * @param userName user name
     * @return true if user name not exist and false if user name existed
     */
    @Override
    public boolean checkExistUserByUserName(String userName) {
        return userRepo.checkExistUserByUserName(userName);
    }

    /**
     * Save insurance data
     *
     * @param registerInsuranceRequest object contains all data to register
     * @throws ParseException   if convert a date invalid
     * @throws RuntimeException if save object to has error
     */
    @Override
    @Transactional
    public void saveUser(RegisterInsuranceRequest registerInsuranceRequest) throws ParseException, RuntimeException {
        User user = new User(registerInsuranceRequest, common);
        int companyId;
        if (registerInsuranceRequest.getCompanyFlag()) {
            companyId = Integer.parseInt(registerInsuranceRequest.getCompanyInternalId());
            user.setCompany(companyRepo.findCompanyById(companyId));
        } else {
            Company company = new Company(registerInsuranceRequest, common);
            companyRepo.save(company);
            user.setCompany(company);
        }
        Insurance insurance = new Insurance(registerInsuranceRequest, common);
        insuranceRepo.save(insurance);
        user.setInsurance(insurance);
        userRepo.saveUser(user);
    }

    /**
     * Get data user map
     *
     * @param userFullName      user full name
     * @param insuranceNumber   insurance number
     * @param placeOfRegister   place of register
     * @param sortType          sort type
     * @param companyIdSelected company id is selected by user
     * @param totalUser         total number of user found
     * @param currentPage       current page is selected by user
     * @return a map processed
     */
    @Override
    public Map<String, Object> getUserMapData(String userFullName, String insuranceNumber, String placeOfRegister, String sortType, int companyIdSelected, int totalUser, int currentPage) {
        List<Integer> pageList = common.getListPaging(totalUser, currentPage, Constant.LIMIT_PAGE);
        int offset = common.getOffset(currentPage);
        int totalPage = common.getTotalPage(totalUser, Constant.LIMIT_PAGE);
        int previousPage = common.getPreviousPage(currentPage);
        int nextPage = common.getNextPage(currentPage, totalPage);
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
