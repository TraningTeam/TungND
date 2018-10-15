package com.validate;

import com.model.RegisterInsuranceRequest;
import com.service.CompanyService;
import com.service.InsuranceService;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.Calendar;

@Component
public class InsuranceValidation {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;


    /**
     * Validate fields of {@code registerInsuranceRequest}
     *
     * @param registerInsuranceRequest object contains fields
     * @param errors                   object contains all of error
     * @return true if @{}
     */
    public boolean validate(RegisterInsuranceRequest registerInsuranceRequest, Errors errors) {
        String insuranceNumber = registerInsuranceRequest.getInsuranceNumber();
        String userName = registerInsuranceRequest.getUserName();
        String password = registerInsuranceRequest.getPassword();
        String rePassword = registerInsuranceRequest.getRePassword();
        String userFullName = registerInsuranceRequest.getUserFullName();
        String birthDate = registerInsuranceRequest.getBirthDate();
        boolean companyFlag = registerInsuranceRequest.getCompanyFlag();
        String companyName = registerInsuranceRequest.getCompanyName();
        String companyAddress = registerInsuranceRequest.getCompanyAddress();
        String companyEmail = registerInsuranceRequest.getCompanyEmail();
        String companyTelephone = registerInsuranceRequest.getCompanyTelephone();
        String placeOfRegister = registerInsuranceRequest.getPlaceOfRegister();
        String insuranceStartDate = registerInsuranceRequest.getInsuranceStartDate();
        String insuranceEndDate = registerInsuranceRequest.getInsuranceEndDate();

        if (Common.checkStringEmptyOrNull(insuranceNumber) == false) {
            errors.rejectValue("insuranceNumber", "message.error.insurance_number.01");
        } else if (Common.checkRegexInsuranceNumber(insuranceNumber) == false) {
            errors.rejectValue("insuranceNumber", "message.error.insurance_number.02");
        } else if (insuranceService.checkExistInsuranceNumber(insuranceNumber) == false) {
            errors.rejectValue("insuranceNumber", "message.error.insurance_number.03");
        }

        if (Common.checkStringEmptyOrNull(userFullName) == false) {
            errors.rejectValue("userFullName", "message.error.user_full_name.01");
        } else if (Common.checkMaxLength(userFullName, 51) == false) {
            errors.rejectValue("userFullName", "message.error.user_full_name.02");
        }

        if (Common.checkStringEmptyOrNull(userName) == false) {
            errors.rejectValue("userName", "message.error.username.01");
        } else if (Common.checkMaxLength(userName, 16) == false) {
            errors.rejectValue("userName", "message.error.username.02");
        } else if (userService.checkExistUserByUserName(userName) == false) {
            errors.rejectValue("userName", "message.error.username.03");
        }

        if (Common.checkStringEmptyOrNull(password) == false) {
            errors.rejectValue("userName", "message.error.password.01");
        } else if (Common.checkMaxLength(password, 33) == false) {
            errors.rejectValue("userName", "message.error.password.02");
        }

        if (Common.checkStringEmptyOrNull(password) == true
                && Common.checkStringEmptyOrNull(rePassword) == true
                && Common.compareString(password, rePassword) == false) {
            errors.rejectValue("userName", "message.error.re_password.01");
        }
        if (Common.checkStringEmptyOrNull(birthDate) == true &&
                (Common.checkFormatDate(birthDate) == false
                        || Common.checkValidBirthdate(birthDate, Calendar.getInstance().getTime()) == false)) {
            errors.rejectValue("birthDate", "message.error.birth_date.01");
        }

        if (companyFlag == false) {
            if (Common.checkStringEmptyOrNull(companyName) == false) {
                errors.rejectValue("companyName", "message.error.company_name.01");
            } else if (Common.checkMaxLength(companyName, 51) == false) {
                errors.rejectValue("companyName", "message.error.company_name.02");
            } else if (companyService.checkExistCompanyByCompanyName(companyName) == false) {
                errors.rejectValue("companyName", "message.error.company_name.03");
            }
            if (Common.checkStringEmptyOrNull(companyAddress) == false) {
                errors.rejectValue("companyAddress", "message.error.company_address.01");
            } else if (Common.checkMaxLength(companyAddress, 101) == false) {
                errors.rejectValue("companyAddress", "message.error.company_address.02");
            }
            if (Common.checkStringEmptyOrNull(companyEmail) == true
                    && Common.checkMaxLength(companyEmail, 51) == false) {
                errors.rejectValue("companyEmail", "message.error.company_email.01");
            }
            if (Common.checkStringEmptyOrNull(companyTelephone) == true
                    && Common.checkRegexTelephone(companyTelephone) == false) {
                errors.rejectValue("companyTelephone", "message.error.company_telephone.01");
            }
        }

        if (Common.checkStringEmptyOrNull(placeOfRegister) == false) {
            errors.rejectValue("placeOfRegister", "message.error.place_of_register.01");
        } else if (Common.checkMaxLength(placeOfRegister, 51) == false) {
            errors.rejectValue("placeOfRegister", "message.error.place_of_register.02");
        }

        if (Common.checkStringEmptyOrNull(insuranceStartDate) == false) {
            errors.rejectValue("insuranceStartDate", "message.error.insurance_start_date.01");
        } else if (Common.checkFormatDate(insuranceStartDate) == false) {
            errors.rejectValue("insuranceStartDate", "message.error.insurance_start_date.02");
        } else if (Common.checkStringEmptyOrNull(insuranceEndDate) == false) {
            errors.rejectValue("insuranceEndDate", "message.error.insurance_end_date.01");
        } else if (Common.checkFormatDate(insuranceEndDate) == false) {
            errors.rejectValue("insuranceEndDate", "message.error.insurance_end_date.02");
        } else if (Common.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate) == false) {
            errors.rejectValue("insuranceEndDate", "message.error.insurance_end_date.03");
        }
        return errors.hasErrors();
    }
}
