package com.validate;

import com.service.CompanyService;
import com.service.InsuranceService;
import com.service.UserService;
import com.util.Common;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

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


    @Test
    public void testValidateInsuranceNumber() {
        //setup
        String insuranceNumber = "";
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        //exersize
        sut.validateInsuranceNumber(insuranceNumber, bindingResult);

        //verify
        Assert.assertEquals(bindingResult.getErrorCount(), 1);
    }

}
