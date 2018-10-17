package com.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CommonTest {

    @InjectMocks
    Common sut;

    /**
     * Veriry list paging
     */
    @Test
    public void testGetListPaging() {
        //setup
        int totalUser = 0;
        int currentPage = 1;
        int limitPage = 5;
        List<Integer> pagingList = new ArrayList<>();

        //verify
        assertEquals(sut.getListPaging(totalUser, currentPage, limitPage), pagingList);
    }

    /**
     * Veriry list paging
     */
    @Test
    public void testGetListPaging2() {
        //setup
        int totalUser = 1;
        int currentPage = 1;
        int limitPage = 5;
        List<Integer> pagingList = Arrays.asList(1);

        //verify
        assertEquals(sut.getListPaging(totalUser, currentPage, limitPage), pagingList);
    }

    /**
     * Veriry list paging
     */
    @Test
    public void testGetListPaging3() {
        //setup
        int totalUser = 1;
        int currentPage = 2;
        int limitPage = 5;
        List<Integer> pagingList = Arrays.asList(1);

        //verify
        assertEquals(sut.getListPaging(totalUser, currentPage, limitPage), pagingList);
    }

    /**
     * Veriry list paging
     */
    @Test
    public void testGetListPaging4() {
        //setup
        int totalUser = 25;
        int currentPage = 2;
        int limitPage = 5;
        List<Integer> pagingList = Arrays.asList(1, 2, 3, 4, 5);

        //verify
        assertEquals(sut.getListPaging(totalUser, currentPage, limitPage), pagingList);
    }

    /**
     * Veriry list paging
     */
    @Test
    public void testGetListPaging5() {
        //setup
        int totalUser = 26;
        int currentPage = 4;
        int limitPage = 5;
        List<Integer> pagingList = Arrays.asList(2, 3, 4, 5, 6);

        //verify
        assertEquals(sut.getListPaging(totalUser, currentPage, limitPage), pagingList);
    }

    /**
     * Veriry list paging
     */
    @Test
    public void testGetListPaging6() {
        //setup
        int totalUser = 26;
        int currentPage = 5;
        int limitPage = 5;
        List<Integer> pagingList = Arrays.asList(2, 3, 4, 5, 6);

        //verify
        assertEquals(sut.getListPaging(totalUser, currentPage, limitPage), pagingList);
    }

    /**
     * Veriry next page
     */
    @Test
    public void testGetNextPage1() {
        //setup
        int currentPage = 1;
        int totalPage = 0;
        int nextPage = 0;

        //verify
        assertEquals(sut.getNextPage(currentPage, totalPage), nextPage);
    }

    /**
     * Veriry next page
     */
    @Test
    public void testGetNextPage2() {
        //setup
        int currentPage = 1;
        int totalPage = 1;
        int nextPage = 0;

        //verify
        assertEquals(sut.getNextPage(currentPage, totalPage), nextPage);
    }

    /**
     * Veriry next page
     */
    @Test
    public void testGetNextPage3() {
        //setup
        int currentPage = 1;
        int totalPage = 2;
        int nextPage = 2;

        //verify
        assertEquals(sut.getNextPage(currentPage, totalPage), nextPage);
    }

    /**
     * Veriry next page
     */
    @Test
    public void testGetNextPage4() {
        //setup
        int currentPage = 2;
        int totalPage = 2;
        int nextPage = 0;

        //verify
        assertEquals(sut.getNextPage(currentPage, totalPage), nextPage);
    }

    /**
     * Veriry previous page
     */
    @Test
    public void testGetPreviousPage() {
        //setup
        int startPage = 2;
        int previousPage = 1;

        //verify
        assertEquals(sut.getPreviousPage(startPage), previousPage);
    }

    /**
     * Veriry previous page
     */
    @Test
    public void testGetStartPage1() {
        //setup
        int currentPage = 1;
        int totalPage = 1;
        int startPage = 1;

        //verify
        assertEquals(sut.getStartPage(currentPage, totalPage), startPage);
    }

    /**
     * Veriry previous page
     */
    @Test
    public void testGetStartPage2() {
        //setup
        int currentPage = 2;
        int totalPage = 6;
        int startPage = 1;

        //verify
        assertEquals(sut.getStartPage(currentPage, totalPage), startPage);
    }

    /**
     * Veriry previous page
     */
    @Test
    public void testGetStartPage3() {
        //setup
        int currentPage = 3;
        int totalPage = 6;
        int startPage = 1;

        //verify
        assertEquals(sut.getStartPage(currentPage, totalPage), startPage);
    }

    /**
     * Veriry previous page
     */
    @Test
    public void testGetStartPage4() {
        //setup
        int currentPage = 4;
        int totalPage = 6;
        int startPage = 2;

        //verify
        assertEquals(sut.getStartPage(currentPage, totalPage), startPage);
    }

    /**
     * Veriry end page
     */
    @Test
    public void testGetEndPage1() {
        //setup
        int startPage = 1;
        int limitPage = 5;
        int totalPage = 0;
        int endPage = 0;

        //verify
        assertEquals(sut.getEndPage(startPage, limitPage, totalPage), endPage);
    }

    /**
     * Veriry end page
     */
    @Test
    public void testGetEndPage2() {
        //setup
        int startPage = 1;
        int limitPage = 5;
        int totalPage = 5;
        int endPage = 5;

        //verify
        assertEquals(sut.getEndPage(startPage, limitPage, totalPage), endPage);
    }

    /**
     * Veriry end page
     */
    @Test
    public void testGetEndPage3() {
        //setup
        int startPage = 2;
        int limitPage = 5;
        int totalPage = 7;
        int endPage = 6;

        //verify
        assertEquals(sut.getEndPage(startPage, limitPage, totalPage), endPage);
    }

    /**
     * Veriry total page
     */
    @Test
    public void testGetTotalPage1() {
        //setup
        int totalUser = 0;
        int limitPage = 5;
        int totalPage = 0;

        //verify
        assertEquals(sut.getTotalPage(totalUser, limitPage), totalPage);
    }

    /**
     * Veriry total page
     */
    @Test
    public void testGetTotalPage2() {
        //setup
        int totalUser = 10;
        int limitPage = 5;
        int totalPage = 2;

        //verify
        assertEquals(sut.getTotalPage(totalUser, limitPage), totalPage);
    }

    /**
     * Veriry total page
     */
    @Test
    public void testGetTotalPage3() {
        //setup
        int totalUser = 11;
        int limitPage = 5;
        int totalPage = 3;

        //verify
        assertEquals(sut.getTotalPage(totalUser, limitPage), totalPage);
    }

    /**
     * Veriry offet
     */
    @Test
    public void testGetOffset1() {
        //setup
        int currentPage = 1;
        int offset = 0;

        //verify
        assertEquals(sut.getOffset(currentPage), offset);
    }

    /**
     * Veriry offet
     */
    @Test
    public void testGetOffset2() {
        //setup
        int currentPage = 2;
        int offset = 5;

        //verify
        assertEquals(sut.getOffset(currentPage), offset);
    }

    /**
     * Veriry string null or empty or not
     */
    @Test
    public void testCheckStringEmptyOrNull1() {
        //setup
        String text = null;
        boolean check = false;

        //verify
        assertEquals(sut.checkStringEmptyOrNull(text), check);
    }

    /**
     * Veriry string null or empty or not
     */
    @Test
    public void testCheckStringEmptyOrNull2() {
        //setup
        String text = "";
        boolean check = false;

        //verify
        assertEquals(sut.checkStringEmptyOrNull(text), check);
    }

    /**
     * Veriry string null or empty or not
     */
    @Test
    public void testCheckStringEmptyOrNull3() {
        //setup
        String text = " text!@$^&";
        boolean check = true;

        //verify
        assertEquals(sut.checkStringEmptyOrNull(text), check);
    }

    /**
     * Veriry insurance number regex
     */
    @Test
    public void testCheckRegexInsuranceNumber1() {
        //setup
        String text = "";
        boolean check = false;

        //verify
        assertEquals(sut.checkRegexInsuranceNumber(text), check);
    }

    /**
     * Veriry insurance number regex
     */
    @Test
    public void testCheckRegexInsuranceNumber2() {
        //setup
        String text = "3213213e23";
        boolean check = false;

        //verify
        assertEquals(sut.checkRegexInsuranceNumber(text), check);
    }

    /**
     * Veriry insurance number regex
     */
    @Test
    public void testCheckRegexInsuranceNumber3() {
        //setup
        String text = "!@#%334562";
        boolean check = false;

        //verify
        assertEquals(sut.checkRegexInsuranceNumber(text), check);
    }

    /**
     * Veriry insurance number regex
     */
    @Test
    public void testCheckRegexInsuranceNumber4() {
        //setup
        String text = "3327896467";
        boolean check = true;

        //verify
        assertEquals(sut.checkRegexInsuranceNumber(text), check);
    }

    /**
     * Veriry max length of string
     */
    @Test
    public void testCheckMaxLength1() {
        //setup
        String text = "da";
        int maxLength = 1;
        boolean check = false;

        //verify
        assertEquals(sut.checkMaxLength(text, maxLength), check);
    }

    /**
     * Veriry max length of string
     */
    @Test
    public void testCheckMaxLength2() {
        //setup
        String text = "da";
        int maxLength = 2;
        boolean check = true;

        //verify
        assertEquals(sut.checkMaxLength(text, maxLength), check);
    }

    /**
     * Veriry max length of string
     */
    @Test
    public void testCheckMaxLength3() {
        //setup
        String text = "da";
        int maxLength = 3;
        boolean check = true;

        //verify
        assertEquals(sut.checkMaxLength(text, maxLength), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate1() {
        //setup
        String insuranceStartDate = "";
        String insuranceEndDate = "20/01/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate2() {
        //setup
        String insuranceStartDate = "da";
        String insuranceEndDate = "20/01/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate3() {
        //setup
        String insuranceStartDate = "01/31/2018";
        String insuranceEndDate = "20/01/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate4() {
        //setup
        String insuranceStartDate = "29/02/2018";
        String insuranceEndDate = "30/03/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate5() {
        //setup
        String insuranceStartDate = "20/01/2018";
        String insuranceEndDate = "";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate6() {
        //setup
        String insuranceStartDate = "20/01/2018";
        String insuranceEndDate = "da";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate7() {
        //setup
        String insuranceStartDate = "20/01/2018";
        String insuranceEndDate = "01/31/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate8() {
        //setup
        String insuranceStartDate = "30/01/2018";
        String insuranceEndDate = "29/02/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate9() {
        //setup
        String insuranceStartDate = "30/01/2018";
        String insuranceEndDate = "30/01/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate10() {
        //setup
        String insuranceStartDate = "30/01/2018";
        String insuranceEndDate = "12/01/2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry insurance date is valid or not
     */
    @Test
    public void testCheckValidInsuranceDate11() {
        //setup
        String insuranceStartDate = "30/01/2018";
        String insuranceEndDate = "20/02/2018";
        boolean check = true;

        //verify
        assertEquals(sut.checkValidInsuranceDate(insuranceStartDate, insuranceEndDate), check);
    }

    /**
     * Veriry telephone regex
     */
    @Test
    public void testCheckRegexTelephone1() {
        //setup
        String text = "1233-1233-1232";
        boolean check = false;

        //verify
        assertEquals(sut.checkRegexTelephone(text), check);
    }

    /**
     * Veriry telephone regex
     */
    @Test
    public void testCheckRegexTelephone2() {
        //setup
        String text = "22-22-22";
        boolean check = false;

        //verify
        assertEquals(sut.checkRegexTelephone(text), check);
    }

    /**
     * Veriry telephone regex
     */
    @Test
    public void testCheckRegexTelephone3() {
        //setup
        String text = "1347-1253-23";
        boolean check = true;

        //verify
        assertEquals(sut.checkRegexTelephone(text), check);
    }

    /**
     * Verify format date to string
     */
    @Test
    public void testFormatDate() {
        //setup
        Date date = new Date(118, 00, 31);
        String dateString = "31/01/2018";

        //verify
        assertEquals(sut.formatDate(date), dateString);
    }

    /**
     * Verify format date to string
     */
    @Test
    public void testCheckFormatDate1() {
        //setup
        String dateString = "test";
        boolean check = false;

        //verify
        assertEquals(sut.checkFormatDate(dateString), check);
    }

    /**
     * Verify format date to string
     */
    @Test
    public void testCheckFormatDate2() {
        //setup
        String dateString = "31-01-2018";
        boolean check = false;

        //verify
        assertEquals(sut.checkFormatDate(dateString), check);
    }

    /**
     * Verify format date to string
     */
    @Test
    public void testCheckFormatDate3() {
        //setup
        String dateString = "2018/01/31";
        boolean check = false;

        //verify
        assertEquals(sut.checkFormatDate(dateString), check);
    }

    /**
     * Verify format date to string
     */
    @Test
    public void testCheckFormatDate4() {
        //setup
        String dateString = "28/02/2018";
        boolean check = true;

        //verify
        assertEquals(sut.checkFormatDate(dateString), check);
    }

    /**
     * Verify format date of birthdate
     */
    @Test
    public void testCheckValidBirthdate1() {
        //setup
        String dateString = "da";
        Date currentDate = Calendar.getInstance().getTime();
        boolean check = false;

        //verify
        assertEquals(sut.checkValidBirthdate(dateString, currentDate), check);
    }

    /**
     * Verify format date of birthdate
     */
    @Test
    public void testCheckValidBirthdate2() {
        //setup
        String dateString = "01/31/2018";
        Date currentDate = Calendar.getInstance().getTime();
        boolean check = false;

        //verify
        assertEquals(sut.checkValidBirthdate(dateString, currentDate), check);
    }

    /**
     * Verify format date of birthdate
     */
    @Test
    public void testCheckValidBirthdate3() {
        //setup
        String dateString = "29/02/2018";
        Date currentDate = Calendar.getInstance().getTime();
        boolean check = false;

        //verify
        assertEquals(sut.checkValidBirthdate(dateString, currentDate), check);
    }

    /**
     * Verify format date of birthdate
     */
    @Test
    public void testCheckValidBirthdate4() {
        //setup
        String dateString = "30/11/3019";
        Date currentDate = Calendar.getInstance().getTime();
        boolean check = false;

        //verify
        assertEquals(sut.checkValidBirthdate(dateString, currentDate), check);
    }

    /**
     * Verify format date of birthdate
     */
    @Test
    public void testCheckValidBirthdate5() {
        //setup
        String dateString = "20/01/1995";
        Date currentDate = Calendar.getInstance().getTime();
        boolean check = true;

        //verify
        assertEquals(sut.checkValidBirthdate(dateString, currentDate), check);
    }

    /**
     * Verify convert string to date
     */
    @Test
    public void testConvertStringToDate1() {
        //setup
        String dateString = "01/31/1995";
        Date date = null;

        //verify
        try {
            assertEquals(sut.convertStringToDate(dateString), date);
            Assert.fail();
        } catch (ParseException parseException) {
            String expectedMessage = "Unparseable date: \"01/31/1995\"";
            assertEquals(expectedMessage, parseException.getMessage());
        }
    }

    /**
     * Verify convert string to date
     */
    @Test
    public void testConvertStringToDate2() {
        //setup
        String dateString = "29/02/2018";
        Date date = null;

        //verify
        try {
            assertEquals(sut.convertStringToDate(dateString), date);
            Assert.fail();
        } catch (ParseException parseException) {
            String expectedMessage = "Unparseable date: \"29/02/2018\"";
            assertEquals(expectedMessage, parseException.getMessage());
        }
    }

    /**
     * Verify convert string to date
     *
     * @throws ParseException if convert date string to date throws exception
     */
    @Test
    public void testConvertStringToDate3() throws ParseException {
        //setup
        String dateString = "31/01/2018";
        Date date = new Date(118, 00, 31);

        //verify
        assertEquals(sut.convertStringToDate(dateString), date);
    }

    /**
     * Verify format text
     */
    @Test
    public void testFormatText() {
        //setup
        String text = "      ngu@#%$^$%yễn    Dương    Tùng";
        String result = "Nguyen Duong Tung ";

        //verify
        assertEquals(sut.formatText(text), result);
    }

    /**
     * Verify unaccent text
     */
    @Test
    public void testUnAccent() {
        //setup
        String text = "nguyễn tùng";
        String result = "nguyen tung";

        //verify
        assertEquals(sut.unAccent(text), result);
    }

    /**
     * Verify compare two string
     */
    @Test
    public void testCompareTwoString1() {
        //setup
        String text1 = "nguyễn phong";
        String text2 = "nguyen tung";
        boolean check = false;

        //verify
        assertEquals(sut.compareString(text1, text2), check);
    }

    /**
     * Verify compare two string
     */
    @Test
    public void testCompareTwoString2() {
        //setup
        String text1 = "nguyễn tung";
        String text2 = "nguyễn tung";
        boolean check = true;

        //verify
        assertEquals(sut.compareString(text1, text2), check);
    }

    /**
     * Verify set format valid date
     */
    @Test
    public void testSetFormatValidDate() {
        //setup
        DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DD_MM_YYYY);
        dateFormat.setLenient(false);

        //verify
        assertEquals(sut.setFormatValidDate(), dateFormat);
    }
}
