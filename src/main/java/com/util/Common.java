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
     * Hàm lấy về danh sách phân trang
     *
     * @param totalUser   là tổng số người dùng khi thực hiện chức năng tìm kiếm
     * @param currentPage là trang hiện tại được chọn
     * @param limitPage   là số phân trang tối đa
     * @return danh sách phân trang
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
     * Hàm lấy về trang kế tiếp
     *
     * @param currentPage
     * @param totalPage
     * @return
     */
    public static int getNextPage(int currentPage, int totalPage) {
        int nextPage = currentPage + 1;
        if (nextPage > totalPage) {
            return 0;
        }
        return nextPage;
    }

    /**
     * @param startPage
     * @return
     */
    public static int getPreviousPage(int startPage) {
        return startPage - 1;
    }

    /**
     * @param startPage
     * @param limitPage
     * @param totalPage
     * @return
     */
    private static int getEndPage(int startPage, int limitPage, int totalPage) {
        if (totalPage > 5) {
            return startPage + limitPage - 1;
        }
        return totalPage;
    }

    /**
     * @param currentPage
     * @param totalPage
     * @return
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
     * @param totalUser
     * @param limitPage
     * @return
     */
    public static int getTotalPage(int totalUser, int limitPage) {
        return (int) Math.ceil((double) totalUser / limitPage);
    }

    /**
     * @param currentPage
     * @return
     */
    public static int getOffset(int currentPage) {
        return (currentPage - 1) * Constant.LIMIT_USER;
    }

    public static String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    /**
     * @param data
     * @return
     */
    public static boolean checkStringEmptyOrNull(String data) {
        if (data == null || data.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * @param insuranceNumber
     * @return
     */
    public static boolean checkRegexInsuranceNumber(String insuranceNumber) {
        return insuranceNumber.matches("^\\d{10}$");
    }

    /**
     * @param field
     * @param length
     * @return
     */
    public static boolean checkMaxLength(String field, int length) {
        return field.length() <= length;
    }

    /**
     * @param userName
     * @return
     */
    public static boolean checkMaxLengthUserName(String userName) {
        return userName.length() < 16;
    }

    /**
     * @param password
     * @return
     */
    public static boolean checkMaxLengthPassword(String password) {
        return password.length() < 33;
    }

    /**
     * @param insuranceStartDate
     * @param insuranceEndDate
     * @return
     */
    public static boolean checkValidInsuranceDate(String insuranceStartDate, String insuranceEndDate) {
        DateFormat dateFormat = setFormatValidDate();
        boolean check = false;
        try {
            check = dateFormat.parse(insuranceStartDate).before(dateFormat.parse(insuranceEndDate));
        } catch (ParseException parseException) {
            return false;
        }
        return check;
    }

    /**
     * @return
     */
    private static DateFormat setFormatValidDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        return dateFormat;
    }

    /**
     * @param birthDate
     * @param currentDate
     * @return
     */
    public static boolean checkValidBirthdate(String birthDate, Date currentDate) {
        DateFormat dateFormat = setFormatValidDate();
        boolean check = false;
        try {
            check = dateFormat.parse(birthDate).before(currentDate);
        } catch (ParseException parseException) {
            return false;
        }
        return check;
    }

    /**
     * @param companyEmail
     * @return
     */
    public static boolean checkRegexEmail(String companyEmail) {
        return companyEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    /**
     * @param companyTelephone
     * @return
     */
    public static boolean checkRegexTelephone(String companyTelephone) {
        return companyTelephone.matches("^(?=(\\d{2,4}-){2}(\\d{2,4})).{12}$");

    }

    /**
     * @param date
     * @return
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
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    /**
     * Hàm chuyển 1 chuỗi bất ký thành 1 chuỗi tiếng việt không dấu,
     * Viết hoa chữ cái đầu mỗi từ
     *
     * @param text chuỗi ký tự
     * @return chuỗi sau khi format
     */
    public static String formatText(String text) {
        // chuyển về viết thường không dấu
        String s = unAccent(text.trim()).toLowerCase();
        //loại bỏ ký tự không phải chữ latin
        s = s.replaceAll("[^A-Za-z\\s]", "");

        String s2[] = s.split(" ");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < s2.length; i++) {
            // kiểm tra khoảng trắng
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
     * Hàm chuyển ký tự tiếng việt có dấu về không dấu
     *
     * @param str là chuỗi ký tự
     * @return chuỗi ký tự không dấu
     */
    public static String unAccent(String str) {
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
    }

    /**
     * @param password
     * @param rePassword
     * @return
     */
    public static boolean comparePasswordAndRepassword(String password, String rePassword) {
        return password.equals(rePassword);
    }
}
