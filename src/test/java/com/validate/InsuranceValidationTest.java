package com.validate;

import com.service.CompanyService;
import com.service.InsuranceService;
import com.service.UserService;
import com.util.Common;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.validation.Errors;

import java.util.Calendar;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InsuranceValidationTest {

    @InjectMocks
    InsuranceValidation sut;

    @Mock
    InsuranceService insuranceService;

    @Mock
    UserService userService;

    @Mock
    CompanyService companyService;

    @Mock
    Common common;

    @Mock
    Errors errors;

    /**
     *
     */
    @Test
    public void testValidateInsuranceNumber1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceNumber(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("insuranceNumber", "message.error.insurance_number.01");
    }

    @Test
    public void testValidateInsuranceNumber2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkRegexInsuranceNumber(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceNumber(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceNumber", "message.error.insurance_number.01");
        verify(errors, times(1)).rejectValue("insuranceNumber", "message.error.insurance_number.02");
    }


    @Test
    public void testValidateInsuranceNumber3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkRegexInsuranceNumber(anyString())).thenReturn(true);
        when(insuranceService.checkExistInsuranceNumber(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceNumber(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceNumber", "message.error.insurance_number.01");
        verify(errors, times(0)).rejectValue("insuranceNumber", "message.error.insurance_number.02");
        verify(errors, times(1)).rejectValue("insuranceNumber", "message.error.insurance_number.03");
    }

    @Test
    public void testValidateInsuranceNumber4() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkRegexInsuranceNumber(anyString())).thenReturn(true);
        when(insuranceService.checkExistInsuranceNumber(anyString())).thenReturn(true);

        //exercise
        sut.validateInsuranceNumber(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceNumber", "message.error.insurance_number.01");
        verify(errors, times(0)).rejectValue("insuranceNumber", "message.error.insurance_number.02");
        verify(errors, times(0)).rejectValue("insuranceNumber", "message.error.insurance_number.03");
    }

    @Test
    public void testValidateUserFullName1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateUserFullName(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("userFullName", "message.error.user_full_name.01");
    }

    @Test
    public void testValidateUserFullName2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);

        //exercise
        sut.validateUserFullName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("userFullName", "message.error.user_full_name.01");
        verify(errors, times(1)).rejectValue("userFullName", "message.error.user_full_name.02");
    }

    @Test
    public void testValidateUserFullName3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);

        //exercise
        sut.validateUserFullName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("userFullName", "message.error.user_full_name.01");
        verify(errors, times(0)).rejectValue("userFullName", "message.error.user_full_name.02");
    }

    @Test
    public void testValidateUserName1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateUserName(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("userName", "message.error.username.01");
    }

    @Test
    public void testValidateUserName2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);

        //exercise
        sut.validateUserName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("userName", "message.error.username.01");
        verify(errors, times(1)).rejectValue("userName", "message.error.username.02");
    }


    @Test
    public void testValidateUserName3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);
        when(userService.checkExistUserByUserName(anyString())).thenReturn(false);

        //exercise
        sut.validateUserName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("userName", "message.error.username.01");
        verify(errors, times(0)).rejectValue("userName", "message.error.username.02");
        verify(errors, times(1)).rejectValue("userName", "message.error.username.03");
    }

    @Test
    public void testValidateUserName4() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);
        when(userService.checkExistUserByUserName(anyString())).thenReturn(true);

        //exercise
        sut.validateUserName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("userName", "message.error.username.01");
        verify(errors, times(0)).rejectValue("userName", "message.error.username.02");
        verify(errors, times(0)).rejectValue("userName", "message.error.username.03");
    }

    @Test
    public void testValidatePasswordAndRePassword1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validatePasswordAndRePassword(anyString(), "", errors);

        //verify
        verify(errors, times(1)).rejectValue("password", "message.error.password.01");
    }

    @Test
    public void testValidatePasswordAndRePassword2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object argument = invocation.getArguments()[0];
                if (argument.equals("true")) {
                    return true;
                }
                return false;
            }
        });
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);
        when(common.compareString(anyString(), anyString())).thenReturn(false);

        //exercise
        sut.validatePasswordAndRePassword("true", "false", errors);

        //verify
        verify(errors, times(1)).rejectValue("password", "message.error.password.02");
        verify(errors, times(0)).rejectValue("rePassword", "message.error.re_password.01");
    }

    @Test
    public void testValidatePasswordAndRePassword3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object argument = invocation.getArguments()[0];
                if (argument.equals("true")) {
                    return true;
                }
                return false;
            }
        });
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);
        when(common.compareString(anyString(), anyString())).thenReturn(false);

        //exercise
        sut.validatePasswordAndRePassword("true", "true", errors);

        //verify
        verify(errors, times(1)).rejectValue("password", "message.error.password.02");
        verify(errors, times(1)).rejectValue("rePassword", "message.error.re_password.01");
    }

    @Test
    public void testValidatePasswordAndRePassword4() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object argument = invocation.getArguments()[0];
                if (argument.equals("true")) {
                    return true;
                }
                return false;
            }
        });
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);
        when(common.compareString(anyString(), anyString())).thenReturn(false);

        //exercise
        sut.validatePasswordAndRePassword("true", "true", errors);

        //verify
        verify(errors, times(0)).rejectValue("password", "message.error.password.02");
        verify(errors, times(1)).rejectValue("rePassword", "message.error.re_password.01");
    }

    @Test
    public void testValidateBirthDate1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkFormatDate(anyString())).thenReturn(false);
        when(common.checkValidBirthdate("", Calendar.getInstance().getTime())).thenReturn(false);

        //exercise
        sut.validateBirthDate(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("birthDate", "message.error.birth_date.01");

    }

    @Test
    public void testValidateBirthDate2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);
        when(common.checkFormatDate(anyString())).thenReturn(false);
        when(common.checkValidBirthdate("", Calendar.getInstance().getTime())).thenReturn(false);

        //exercise
        sut.validateBirthDate(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("birthDate", "message.error.birth_date.01");
    }

    @Test
    public void testValidateCompanyName1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateCompanyName(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("companyName", "message.error.company_name.01");
    }

    @Test
    public void testValidateCompanyName2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);

        //exercise
        sut.validateCompanyName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("companyName", "message.error.company_name.01");
        verify(errors, times(1)).rejectValue("companyName", "message.error.company_name.02");
    }

    @Test
    public void testValidateCompanyName3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);
        when(companyService.checkExistCompanyByCompanyName(anyString())).thenReturn(false);

        //exercise
        sut.validateCompanyName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("companyName", "message.error.company_name.01");
        verify(errors, times(0)).rejectValue("companyName", "message.error.company_name.02");
        verify(errors, times(1)).rejectValue("companyName", "message.error.company_name.03");
    }

    @Test
    public void testValidateCompanyAddress1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateCompanyAddress(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("companyAddress", "message.error.company_address.01");
    }

    @Test
    public void testValidateCompanyAddress2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);

        //exercise
        sut.validateCompanyAddress(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("companyAddress", "message.error.company_address.01");
        verify(errors, times(1)).rejectValue("companyAddress", "message.error.company_address.02");
    }

    @Test
    public void testValidateCompanyAddress3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);

        //exercise
        sut.validateCompanyName(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("companyAddress", "message.error.company_address.01");
        verify(errors, times(0)).rejectValue("companyAddress", "message.error.company_address.02");
    }

    @Test
    public void testValidateCompanyEmail1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);

        //exercise
        sut.validateCompanyEmail(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("companyEmail", "message.error.company_email.01");
    }

    @Test
    public void testValidateCompanyEmail2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);

        //exercise
        sut.validateCompanyEmail(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("companyEmail", "message.error.company_email.01");
    }

    @Test
    public void testValidateCompanyTelephone1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkRegexTelephone(anyString())).thenReturn(true);

        //exercise
        sut.validateCompanyTelephone(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("companyTelephone", "message.error.company_telephone.01");
    }

    @Test
    public void testValidateCompanyTelephone2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkRegexTelephone(anyString())).thenReturn(false);

        //exercise
        sut.validateCompanyTelephone(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("companyTelephone", "message.error.company_telephone.01");
    }

    @Test
    public void testValidatePlaceOfRegister1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validatePlaceOfRegister(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("placeOfRegister", "message.error.place_of_register.01");
    }

    @Test
    public void testValidatePlaceOfRegister2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(false);

        //exercise
        sut.validatePlaceOfRegister(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("placeOfRegister", "message.error.place_of_register.01");
        verify(errors, times(1)).rejectValue("placeOfRegister", "message.error.place_of_register.02");
    }

    @Test
    public void testValidatePlaceOfRegister3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkMaxLength(anyString(), anyInt())).thenReturn(true);

        //exercise
        sut.validatePlaceOfRegister(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("placeOfRegister", "message.error.place_of_register.01");
        verify(errors, times(0)).rejectValue("placeOfRegister", "message.error.place_of_register.02");
    }


    @Test
    public void testValidateInsuranceStartDate1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceStartDate(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("insuranceStartDate", "message.error.insurance_start_date.01");
    }

    @Test
    public void testValidateInsuranceStartDate2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkFormatDate(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceStartDate(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceStartDate", "message.error.insurance_start_date.01");
        verify(errors, times(1)).rejectValue("insuranceStartDate", "message.error.insurance_start_date.02");
    }

    @Test
    public void testValidateInsuranceStartDate3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkFormatDate(anyString())).thenReturn(true);

        //exercise
        sut.validateInsuranceStartDate(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceStartDate", "message.error.insurance_start_date.01");
        verify(errors, times(0)).rejectValue("insuranceStartDate", "message.error.insurance_start_date.02");
    }

    @Test
    public void testValidateInsuranceEndDate1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceEndDate(anyString(), errors);

        //verify
        verify(errors, times(1)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.01");
    }

    @Test
    public void testValidateInsuranceEndDate2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkFormatDate(anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceEndDate(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.01");
        verify(errors, times(1)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.02");
    }

    @Test
    public void testValidateInsuranceEndDate3() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkFormatDate(anyString())).thenReturn(true);

        //exercise
        sut.validateInsuranceEndDate(anyString(), errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.01");
        verify(errors, times(0)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.02");
    }

    @Test
    public void testInsuranceStartDateAndEndDate1() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkValidInsuranceDate(anyString(), anyString())).thenReturn(false);

        //exercise
        sut.validateInsuranceStartDateAndEndDate(anyString(), "", errors);

        //verify
        verify(errors, times(1)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.03");
    }

    @Test
    public void testInsuranceStartDateAndEndDate2() {
        //setup
        when(common.checkStringEmptyOrNull(anyString())).thenReturn(true);
        when(common.checkValidInsuranceDate(anyString(), anyString())).thenReturn(true);

        //exercise
        sut.validateInsuranceStartDateAndEndDate(anyString(), "", errors);

        //verify
        verify(errors, times(0)).rejectValue("insuranceEndDate", "message.error.insurance_end_date.03");
    }

}
