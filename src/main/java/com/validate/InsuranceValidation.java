package com.validate;

import com.model.RegisterInsuranceRequest;
import com.service.CompanyService;
import com.service.InsuranceService;
import com.service.UserService;
import com.util.Common;
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

    @Autowired
    private Common common;


    /**
     * Validate fields of {@code registerInsuranceRequest}
     *
     * @param registerInsuranceRequest object contains fields
     * @param errors                   object contains all of error
     * @return true if {@code errors} has errors and false if {@code errors} not has any errors
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
        validateInsuranceNumber(insuranceNumber, errors);
        validateUserFullName(userFullName, errors);
        validateUserName(userName, errors);
        validatePasswordAndRePassword(password, rePassword, errors);
        validateBirthDate(birthDate, errors);
        validateCompanyInfomation(companyFlag, companyName, companyAddress, companyEmail, companyTelephone, errors);
        validatePlaceOfRegister(placeOfRegister, errors);
        validateInsuranceStartDateAndInsuranceEndDate(insuranceStartDate, insuranceEndDate, errors);
        return errors.hasErrors();
    }

    /**
     * Validate {@code insuranceNumber}
     *
     * @param insuranceNumber field insurance number to validate
     * @param errors          object contains all of error
     */
    public void validateInsuranceNumber(String insuranceNumber, Errors errors) {
        if (common.checkStringEmptyOrNull(insuranceNumber) == false) {
            errors.rejectValue("insuranceNumber", "message.error.insurance_number.01");
        } else if (common.checkRegexInsuranceNumber(insuranceNumber) == false) {
            errors.rejectValue("insuranceNumber", "message.error.insurance_number.02");
        } else if (insuranceService.checkExistInsuranceNumber(insuranceNumber) == false) {
            errors.rejectValue("insuranceNumber", "message.error.insurance_number.03");
        }
    }

    /**
     * Validate {@code userFullName}
     *
     * @param userFullName field user full name to validate
     * @param errors       object contains all of error
     */
    public void validateUserFullName(String userFullName, Errors errors) {
        if (common.checkStringEmptyOrNull(userFullName) == false) {
            errors.rejectValue("userFullName", "message.error.user_full_name.01");
        } else if (common.checkMaxLength(userFullName, 51) == false) {
            errors.rejectValue("userFullName", "message.error.user_full_name.02");
        }
    }

    /**
     * Validate {@code userName}
     *
     * @param userName field user name to validate
     * @param errors   object contains all of error
     */
    public void validateUserName(String userName, Errors errors) {
        if (common.checkStringEmptyOrNull(userName) == false) {
            errors.rejectValue("userName", "message.error.username.01");
        } else if (common.checkMaxLength(userName, 16) == false) {
            errors.rejectValue("userName", "message.error.username.02");
        } else if (userService.checkExistUserByUserName(userName) == false) {
            errors.rejectValue("userName", "message.error.username.03");
        }
    }

    /**
     * Validate {@code password} and {@code rePassword}
     *
     * @param password   field password  to validate
     * @param rePassword field re password to validate
     * @param errors     object contains all of error
     */
    public void validatePasswordAndRePassword(String password, String rePassword, Errors errors) {
        if (common.checkStringEmptyOrNull(password) == false) {
            errors.rejectValue("userName", "message.error.password.01");
        } else if (common.checkMaxLength(password, 33) == false) {
            errors.rejectValue("userName", "message.error.password.02");
        }
        if (common.checkStringEmptyOrNull(password) == true
                && common.checkStringEmptyOrNull(rePassword) == true
                && common.compareString(password, rePassword) == false) {
            errors.rejectValue("userName", "message.error.re_password.01");
        }
    }

    /**
     * Validate {@code birthDate}
     *
     * @param birthDate field birth date to validate
     * @param errors    object contains all of error
     */
    public void validateBirthDate(String birthDate, Errors errors) {
        if (common.checkStringEmptyOrNull(birthDate) == true &&
                (common.checkFormatDate(birthDate) == false
                        || common.checkValidBirthdate(birthDate, Calendar.getInstance().getTime()) == false)) {
            errors.rejectValue("birthDate", "message.error.birth_date.01");
        }
    }

    /**
     * Validate {@code companyName},{@code companyAddress},{@code companyEmail},{@code companyTelephone}
     *
     * @param companyFlag      flag to create new company or exist company
     * @param companyName      field company name to validate
     * @param companyAddress   field company address to validate
     * @param companyEmail     field company email to validate
     * @param companyTelephone field company telephone to validate
     * @param errors           object contains all of error
     */
    public void validateCompanyInfomation(boolean companyFlag, String companyName, String companyAddress, String companyEmail, String companyTelephone, Errors errors) {
        if (companyFlag == false) {
            if (common.checkStringEmptyOrNull(companyName) == false) {
                errors.rejectValue("companyName", "message.error.company_name.01");
            } else if (common.checkMaxLength(companyName, 51) == false) {
                errors.rejectValue("companyName", "message.error.company_name.02");
            } else if (companyService.checkExistCompanyByCompanyName(companyName) == false) {
                errors.rejectValue("companyName", "message.error.company_name.03");
            }
            if (common.checkStringEmptyOrNull(companyAddress) == false) {
                errors.rejectValue("companyAddress", "message.error.company_address.01");
            } else if (common.checkMaxLength(companyAddress, 101) == false) {
                errors.rejectValue("companyAddress", "message.error.company_address.02");
            }
            if (common.checkStringEmptyOrNull(companyEmail) == true
                    && common.checkMaxLength(companyEmail, 51) == false) {
                errors.rejectValue("companyEmail", "message.error.company_email.01");
            }
            if (common.checkStringEmptyOrNull(companyTelephone) == true
                    && common.checkRegexTelephone(companyTelephone) == false) {
                errors.rejectValue("companyTelephone", "message.error.company_telephone.01");
            }
        }
    }

    /**
     * Validate {@code placeOfRegister}
     *
     * @param placeOfRegister field place of register to validate
     * @param errors          object contains all of error
     */
    public void validatePlaceOfRegister(String placeOfRegister, Errors errors) {
        if (common.checkStringEmptyOrNull(placeOfRegister) == false) {
            errors.rejectValue("placeOfRegister", "message.error.place_of_register.01");
        } else if (common.checkMaxLength(placeOfRegister, 51) == false) {
            errors.rejectValue("placeOfRegister", "message.error.place_of_register.02");
        }

    }

    /**
     * Validate {@code insuranceStartDate} and {@code insuranceEndDate}
     *
     * @param insuranceStartDate field insurance start date to validate
     * @param insuranceEndDate   field insurance end date to validate
     * @param errors             object contains all of error
     */
    public void validateInsuranceStartDateAndInsuranceEndDate(String insuranceStartDate, String
            insuranceEndDate, Errors errors) {
        if (common.checkStringEmptyOrNull(insuranceStartDate) == false) {
            errors.rejectValue("insuranceStartDate", "message.error.insurance_start_date.01");
        } else if (common.checkFormatDate(insuranceStartDate) == false) {
            errors.rejectValue("insuranceStartDate", "message.error.insurance_start_date.02");
        } else if (common.checkStringEmptyOrNull(insuranceEndDate) == false) {
            errors.rejectValue("insuranceEndDate", "message.error.insurance_end_date.01");
        } else if (common.checkFormatDate(insuranceEndDate) == false) {
            errors.rejectValue("insuranceEndDate", "message.error.insurance_end_date.02");
        } else if (common.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate) == false) {
            errors.rejectValue("insuranceEndDate", "message.error.insurance_end_date.03");
        }
    }
}
