package com.util;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Common {

    /**
     * Find a list paging
     *
     * @param totalUser   total user when searchTotal number of users found when search
     * @param currentPage index of current page is selected by the user
     * @param limitPage   maximum number of pages to paging
     * @return list index of paging
     */
    public static List<Integer> getListPaging(int totalUser, int currentPage, int limitPage) {
        List<Integer> pageList = new ArrayList<>();
        int totalPage = getTotalPage(totalUser, limitPage);
        int startPage = getStartPage(currentPage, totalPage);
        int endPage = getEndPage(startPage, limitPage, totalPage);
        for (int i = startPage; i <= endPage; i++) {
            pageList.add(i);
        }
        return pageList;
    }

    /**
     * Find index of next page
     *
     * @param currentPage index of current page is selected by the user
     * @param totalPage   total number of pages found when search
     * @return index of next page
     */
    public static int getNextPage(int currentPage, int totalPage) {
        int nextPage = currentPage + 1;
        if (nextPage > totalPage) {
            return 0;
        }
        return nextPage;
    }

    /**
     * Find index of previous page
     *
     * @param startPage index of first page in list paging
     * @return index of previous page
     */
    public static int getPreviousPage(int startPage) {
        return startPage - 1;
    }


    /**
     * Find index of start page in list paging
     *
     * @param currentPage index of current page is selected by the user
     * @param totalPage   total number of pages found when search
     * @return index of start page
     */
    private static int getStartPage(int currentPage, int totalPage) {
        if (totalPage > 5) {
            if (2 < currentPage && currentPage <= totalPage - 2) {
                return currentPage - 2;
            } else if (currentPage > totalPage - 2) {
                return (int) Math.ceil((double) currentPage / 2) - 1;
            }
        }
        return 1;
    }

    /**
     * Find index of end page
     *
     * @param startPage first page in list paging
     * @param limitPage maximum number of pages to paging
     * @param totalPage Total number of pages found when search
     * @return index of end page
     */
    private static int getEndPage(int startPage, int limitPage, int totalPage) {
        if (totalPage > 5) {
            return startPage + limitPage - 1;
        }
        return totalPage;
    }

    /**
     * Find total number of pages
     *
     * @param totalUser total user when search
     * @param limitPage maximum number of pages to paging
     * @return total number of pages
     */
    public static int getTotalPage(int totalUser, int limitPage) {
        return (int) Math.ceil((double) totalUser / limitPage);
    }

    /**
     * Find offset by current page
     *
     * @param currentPage current page is selected by the user
     * @return index of offset
     */
    public static int getOffset(int currentPage) {
        return (currentPage - 1) * Constant.LIMIT_USER;
    }


    /**
     * Check empty string or null string
     *
     * @param str string to check
     * @return true if {@code str} is not null or empty,
     * And false if {@code str} is null or empty
     */
    public static boolean checkStringEmptyOrNull(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Check string {@code insuranceNumber} matches with regex
     *
     * @param insuranceNumber string to regex
     * @return true if {@code insuranceNumber} matches with regex,
     * And false if {@code insuranceNumber} not matches with regex
     */
    public static boolean checkRegexInsuranceNumber(String insuranceNumber) {
        return insuranceNumber.matches("^\\d{10}$");
    }

    /**
     * Check string is less than or equal to max length
     *
     * @param field     field to check
     * @param maxLength max length of string
     * @return true if {@code field} is less than or equal to {@code maxLength},
     * And false if {@code field} is greater than {@code maxLength}
     */
    public static boolean checkMaxLength(String field, int maxLength) {
        return field.length() <= maxLength;
    }

    /**
     * Check insurance start date is less than insurance end date
     *
     * @param insuranceStartDate string of insurance start date
     * @param insuranceEndDate   string of insurance end date
     * @return true if {@code insuranceStartDate} is less than or equal to {@code insuranceEndDate},
     * And false if {@code insuranceStartDate} is greater than {@code insuranceEndDate}
     */
    public static boolean checkValidInsuranceDate(String insuranceStartDate, String insuranceEndDate) {
        DateFormat dateFormat = setFormatValidDate();
        boolean check;
        try {
            check = dateFormat.parse(insuranceStartDate).before(dateFormat.parse(insuranceEndDate));
        } catch (ParseException parseException) {
            return false;
        }
        return check;
    }


    /**
     * Check string {@code companyTelephone} matches with regex
     *
     * @param companyTelephone string to regex
     * @return true if {@code companyTelephone} matches with regex,
     * And false if {@code companyTelephone} not matches with regex
     */
    public static boolean checkRegexTelephone(String companyTelephone) {
        return companyTelephone.matches("^(?=(\\d{2,4}-){2}(\\d{2,4})).{12}$");

    }

    /**
     * Format date to date with dd/MM/yyyy format
     *
     * @param date date to format
     * @return date with dd/MM/yyyy format
     */
    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DD_MM_YYYY);
        return dateFormat.format(date);
    }

    /**
     * Check {@code date} is valid date
     *
     * @param date string date to format
     * @return true if {@code date} is valid date and false if {@code date} is invalid date
     */
    public static boolean checkFormatDate(String date) {
        DateFormat dateFormat = setFormatValidDate();
        try {
            dateFormat.parse(date);
        } catch (ParseException par) {
            return false;
        }
        return true;
    }

    /**
     * Check {@code birthDate} is less than {@code currentDate}
     *
     * @param birthDate   string of birth date
     * @param currentDate current date
     * @return true if {@code birthDate} is less than or equal to {@code currentDate},
     * And false if {@code birthDate} is greater than {@code currentDate}
     */
    public static boolean checkValidBirthdate(String birthDate, Date currentDate) {
        DateFormat dateFormat = setFormatValidDate();
        boolean check;
        try {
            check = dateFormat.parse(birthDate).before(currentDate);
        } catch (ParseException parseException) {
            return false;
        }
        return check;
    }

    /**
     * Convert a string date to date
     *
     * @param date string of date
     * @return date after convert from string
     * @throws ParseException if {@code date} is not a string date or {@code date} is an invalid date
     */
    public static Date convertStringToDate(String date) throws ParseException {
        DateFormat dateFormat = setFormatValidDate();
        return dateFormat.parse(date);
    }

    /**
     * Convert {@code text} to uncompressed string,
     * Upper case the first letter of each word
     *
     * @param text string
     * @return string formatted
     */
    public static String formatText(String text) {
        // convert to uncompressed string
        String s = unAccent(text.trim()).toLowerCase();
        //Remove non-latin characters
        s = s.replaceAll("[^A-Za-z\\s]", "");

        String s2[] = s.split(" ");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s2.length; i++) {
            // check white space
            if (s2[i].length() > 0) {
                //viết hoa ký tự đầu
                s2[i] = s2[i].substring(0, 1).toUpperCase() + s2[i].substring(1);
                result.append(s2[i]);
                result.append(" ");
            }
        }
        return result.toString();
    }

    /**
     * Convert {@code str} to uncompressed string
     *
     * @param str string
     * @return uncompressed string
     */
    public static String unAccent(String str) {
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }

    /**
     * Compare two string
     *
     * @param str1 string first
     * @param str2 string second
     * @return true if {@code str1} is equal to str2 and false if {@code str1} is not equal to str2
     */
    public static boolean compareString(String str1, String str2) {
        return str1.equals(str2);
    }

    /**
     * Set {@code DateFormat} not format a invalid date (Example: 29/02/2017, 31/04/2018,...)
     *
     * @return an object {@code DateFormat}
     */
    private static DateFormat setFormatValidDate() {
        DateFormat dateFormat = new SimpleDateFormat(Constant.FORMAT_DD_MM_YYYY);
        dateFormat.setLenient(false);
        return dateFormat;
    }

}
