package com.service.imp;

import com.model.Company;
import com.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceImpTest {

    @InjectMocks
    CompanyServiceImp sut;

    @Mock
    CompanyRepository companyRepo;

    @Test
    public void testFindAllCompany() {
        //setup
        List<Company> companyList = new ArrayList<>();
        when(companyRepo.findAllCompany()).thenReturn(companyList);

        //exercise
        List<Company> actualCompanyList = sut.findAllCompany();

        //verify
        assertEquals(actualCompanyList, companyList);
    }

    @Test
    public void testFindCompanyById() {
        //setup
        Company company = new Company();
        when(companyRepo.findCompanyById(anyInt())).thenReturn(company);

        //exercise
        Company actualCompany = sut.findCompanyById(anyInt());

        //verify
        assertEquals(actualCompany, company);
    }

    @Test
    public void testCheckExistCompanyByCompanyName1() {
        //setup
        when(companyRepo.findCompanyByName(anyString())).thenReturn(null);

        //exercise
        boolean checkExistCompany = sut.checkExistCompanyByCompanyName(anyString());

        //verify
        assertEquals(checkExistCompany, true);
    }

    @Test
    public void testCheckExistCompanyByCompanyName2() {
        //setup
        when(companyRepo.findCompanyByName(anyString())).thenReturn(new Company());

        //exercise
        boolean checkExistCompany = sut.checkExistCompanyByCompanyName(anyString());

        //verify
        assertEquals(checkExistCompany, false);
    }
}
