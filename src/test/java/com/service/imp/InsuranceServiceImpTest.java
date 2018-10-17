package com.service.imp;

import com.model.Insurance;
import com.repository.InsuranceRepository;
import com.service.CompanyService;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InsuranceServiceImpTest {

    @InjectMocks
    InsuranceServiceImp sut;

    @Mock
    InsuranceRepository insuranceRepo;

    @Mock
    CompanyService companyService;

    @Mock
    UserService userService;

    @Mock
    Insurance insurance;

    @Test
    public void testCheckExistInsuranceNumber1() {
        //setup
        when(insuranceRepo
                .findInsuranceByInsuranceNumber(anyString())).thenReturn(null);

        //exercise
        boolean actual = sut.checkExistInsuranceNumber(anyString());

        //verify
        assertEquals(actual, true);
    }

    @Test
    public void testCheckExistUserByUserName2() {
        //setup
        when(insuranceRepo
                .findInsuranceByInsuranceNumber(anyString())).thenReturn(insurance);

        //exercise
        boolean actual = sut.checkExistInsuranceNumber(anyString());

        //verify
        assertEquals(actual, false);
    }
}
