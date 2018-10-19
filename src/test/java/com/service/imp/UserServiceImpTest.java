package com.service.imp;

import com.model.RegisterInsuranceRequest;
import com.model.User;
import com.repository.CompanyRepository;
import com.repository.InsuranceRepository;
import com.repository.UserRepository;
import com.util.Common;
import com.util.Constant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImpTest {

    @InjectMocks
    UserServiceImp sut;

    @Mock
    UserRepository userRepo;

    @Mock
    CompanyRepository companyRepo;

    @Mock
    InsuranceRepository insuranceRepo;

    @Mock
    Common common;

    /**
     * Test find user
     */
    @Test
    public void testFindUser() {
        //setup
        List<User> userList = new ArrayList<>();
        when(userRepo
                .findUser(anyInt(), anyString(), anyString(), anyString(), anyString(), anyInt(), anyInt())).thenReturn(userList);

        //exercise
        List<User> actualUserList = sut.findUser(anyInt(), anyString(), anyString(), anyString(), anyString(), anyInt(), anyInt());

        //verify
        assertEquals(actualUserList, userList);
    }

    /**
     * Test count total user
     */
    @Test
    public void testCountTotalUser() {
        //setup
        int totalUser = 0;
        when(userRepo
                .countTotalUser(anyInt(), anyString(), anyString(), anyString())).thenReturn(totalUser);

        //exercise
        int actualTotalUsert = sut.countTotalUser(anyInt(), anyString(), anyString(), anyString());

        //verify
        assertEquals(actualTotalUsert, totalUser);
    }

    /**
     * Test find user data
     */
    @Test
    public void testFindUserDataToExport() {
        //setup
        List<User> userList = new ArrayList<>();
        when(userRepo
                .findUserDataToExport(anyInt(), anyString(), anyString(), anyString())).thenReturn(userList);

        //exercise
        List<User> actualUserList = sut.findUserDataToExport(anyInt(), anyString(), anyString(), anyString());

        //verify
        assertEquals(actualUserList, userList);
    }

    /**
     * Test check exist of user by user name
     */
    @Test
    public void testCheckExistUserByUserName1() {
        //setup
        when(userRepo
                .checkExistUserByUserName(anyString())).thenReturn(false);

        //exercise
        boolean actual = sut.checkExistUserByUserName(anyString());

        //verify
        assertEquals(actual, false);
    }

    /**
     * Test check exist of user by user name
     */
    @Test
    public void testCheckExistUserByUserName2() {
        //setup
        when(userRepo
                .checkExistUserByUserName(anyString())).thenReturn(true);

        //exercise
        boolean actual = sut.checkExistUserByUserName(anyString());

        //verify
        assertEquals(actual, true);
    }

    /**
     * Test save user
     */
    @Test
    public void testSaveUser1() {
        try {
            //setup
            RegisterInsuranceRequest registerInsuranceRequest = new RegisterInsuranceRequest();
            registerInsuranceRequest.setUserSexDivision("1");
            registerInsuranceRequest.setCompanyInternalId("1");

            //exercise
            sut.saveUser(registerInsuranceRequest);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //verify
        verify(userRepo, times(1)).saveUser(anyObject());
    }

    /**
     * Test save user
     */
    @Test
    public void testSaveUser2() {
        try {
            //setup
            RegisterInsuranceRequest registerInsuranceRequest = new RegisterInsuranceRequest();
            registerInsuranceRequest.setUserSexDivision("1");
            registerInsuranceRequest.setCompanyFlag(false);

            //exercise
            sut.saveUser(registerInsuranceRequest);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //verify
        verify(userRepo, times(1)).saveUser(anyObject());
    }

    /**
     * Test save user
     */
    @Test
    public void testSaveUser3() {
        try {
            //setup
            RegisterInsuranceRequest registerInsuranceRequest = new RegisterInsuranceRequest();
            registerInsuranceRequest.setUserSexDivision("1");
            registerInsuranceRequest.setCompanyFlag(false);
            when(common.convertStringToDate(registerInsuranceRequest.getInsuranceEndDate())).thenThrow(ParseException.class);

            //exercise
            sut.saveUser(registerInsuranceRequest);
        } catch (ParseException e) {
            System.out.println("Catch Exception");
        }

        //verify
        verify(userRepo, times(0)).saveUser(anyObject());
    }

    /**
     * Test get user data
     */
    @Test
    public void testGetUserMapData() {
        //setup
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(Constant.ATTRIBUTE_NEXT_PAGE, 0);
        userMap.put(Constant.ATTRIBUTE_PAGE_LIST, new ArrayList<Integer>());
        userMap.put(Constant.ATTRIBUTE_TOTAL_PAGE, 0);
        userMap.put(Constant.ATTRIBUTE_PREVIOUS_PAGE, 0);
        userMap.put(Constant.ATTRIBUTE_USER_LIST, new ArrayList<User>());
        userMap.put(Constant.ATTRIBUTE_CURRENT_PAGE, 1);
        when(sut.findUser(1, "", "",
                "", "", Constant.LIMIT_USER, 1)).thenReturn(new ArrayList<User>());

        //exercise
        Map<String, Object> actual = sut.getUserMapData("", "", "", "", 1, 1, 1);
        //verify
        assertEquals(actual, userMap);
    }
}
