package com.service.imp;

import com.model.Company;
import com.model.Insurance;
import com.model.SearchInsuranceRequest;
import com.repository.InsuranceRepository;
import com.service.CompanyService;
import com.service.UserService;
import com.util.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Mock
    HttpSession session;

    /**
     * Test check exist of insurance number
     */
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

    /**
     * Test check exist of insurance number
     */
    @Test
    public void testCheckExistInsuranceNumber2() {
        //setup
        when(insuranceRepo
                .findInsuranceByInsuranceNumber(anyString())).thenReturn(insurance);

        //exercise
        boolean actual = sut.checkExistInsuranceNumber(anyString());

        //verify
        assertEquals(actual, false);
    }

    /**
     * Test get insurance map data
     */
    @Test
    public void testGetInsuranceMapData1() {
        //setup
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyInternalId(1);
        company.setCompanyName("JSC");
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("luvina@luvina.net");
        company.setTelephone("12341444");
        companyList.add(company);
        Map<String, Object> insuranceMap = new HashMap<>();
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, 1);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, "ASC");
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, 0);
        when(companyService.findAllCompany()).thenReturn(companyList);

        //exercise
        Map<String, Object> actual = sut.getInsuranceMapData("", "1", new SearchInsuranceRequest(), "ASC", "1", session);

        //verify
        assertEquals(actual, insuranceMap);
    }

    /**
     * Test get insurance map data
     */
    @Test
    public void testGetInsuranceMapData2() {
        //setup
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyInternalId(1);
        company.setCompanyName("JSC");
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("luvina@luvina.net");
        company.setTelephone("12341444");
        companyList.add(company);
        Map<String, Object> insuranceMap = new HashMap<>();
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, 1);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, "ASC");
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, 0);
        when(companyService.findAllCompany()).thenReturn(companyList);
        when(session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED)).thenReturn(1);

        //exercise
        Map<String, Object> actual = sut.getInsuranceMapData("sort", "1", new SearchInsuranceRequest(), "ASC", "1", session);

        //verify
        assertEquals(actual, insuranceMap);
    }

    /**
     * Test get insurance map data
     */
    @Test
    public void testGetInsuranceMapData3() {
        //setup
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyInternalId(1);
        company.setCompanyName("JSC");
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("luvina@luvina.net");
        company.setTelephone("12341444");
        companyList.add(company);
        Map<String, Object> insuranceMap = new HashMap<>();
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, 1);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, "ASC");
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, 0);
        when(companyService.findAllCompany()).thenReturn(companyList);
        when(session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED)).thenReturn(1);
        when(session.getAttribute(Constant.ATTRIBUTE_SORT_TYPE)).thenReturn("ASC");

        //exercise
        Map<String, Object> actual = sut.getInsuranceMapData("paging", "1", new SearchInsuranceRequest(), "ASC", "1", session);

        //verify
        assertEquals(actual, insuranceMap);
    }

    /**
     * Test get insurance map data
     */
    @Test
    public void testGetInsuranceMapData4() {
        //setup
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyInternalId(1);
        company.setCompanyName("JSC");
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("luvina@luvina.net");
        company.setTelephone("12341444");
        companyList.add(company);
        Map<String, Object> insuranceMap = new HashMap<>();
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, 1);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, "ASC");
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, 0);
        when(companyService.findAllCompany()).thenReturn(companyList);

        //exercise
        Map<String, Object> actual = sut.getInsuranceMapData("search", "1", new SearchInsuranceRequest(), "ASC", "1", session);

        //verify
        assertEquals(actual, insuranceMap);
    }

    /**
     * Test get insurance map data
     */
    @Test
    public void testGetInsuranceMapData5() {
        //setup
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyInternalId(1);
        company.setCompanyName("JSC");
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("luvina@luvina.net");
        company.setTelephone("12341444");
        companyList.add(company);
        Map<String, Object> insuranceMap = new HashMap<>();
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, 1);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, "ASC");
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, 0);
        when(companyService.findAllCompany()).thenReturn(companyList);
        when(session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED)).thenReturn(1);
        when(session.getAttribute(Constant.ATTRIBUTE_CURRENT_PAGE)).thenReturn(1);

        //exercise
        Map<String, Object> actual = sut.getInsuranceMapData("back", "1", new SearchInsuranceRequest(), "ASC", "1", session);

        //verify
        assertEquals(actual, insuranceMap);
    }

    /**
     * Test get insurance map data
     */
    @Test
    public void testGetInsuranceMapData6() {
        //setup
        List<Company> companyList = new ArrayList<>();
        Company company = new Company();
        company.setCompanyInternalId(1);
        company.setCompanyName("JSC");
        company.setAddress("Hoang Quoc Viet");
        company.setEmail("luvina@luvina.net");
        company.setTelephone("12341444");
        companyList.add(company);
        Map<String, Object> insuranceMap = new HashMap<>();
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_ID_SELECTED, 1);
        insuranceMap.put(Constant.ATTRIBUTE_COMPANY_LIST, companyList);
        insuranceMap.put(Constant.ATTRIBUTE_SORT_TYPE, "ASC");
        insuranceMap.put(Constant.ATTRIBUTE_TOTAL_USER, 1);
        insuranceMap.put(Constant.ATTRIBUTE_USER_MAP, new HashMap<>());
        when(companyService.findAllCompany()).thenReturn(companyList);
        when(userService.countTotalUser(1, "", "",
                "")).thenReturn(1);

        //exercise
        Map<String, Object> actual = sut.getInsuranceMapData("search", "1", new SearchInsuranceRequest(), "ASC", "1", session);

        //verify
        assertEquals(actual, insuranceMap);
    }
}
