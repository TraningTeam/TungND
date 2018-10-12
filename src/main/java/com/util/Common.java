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
	 * @param totalUser
	 * @param currentPage
	 * @param limitPage
	 * @return
	 */
	public static List<Integer> getListPaging(int totalUser, int currentPage, int limitPage) {
		List<Integer> pageList = new ArrayList<>();
		int totalPage = getTotalPage(totalUser, limitPage);
		int currentGroupPage = getCurrentGroupPage(currentPage, limitPage);
		int startPage = getStartPage(currentGroupPage, limitPage);
		int endPage = getEndPage(startPage, limitPage, totalPage);
		for (int i = startPage; i <= endPage; i++) {
			pageList.add(i);
		}
		return pageList;
	}
	
	public static int getNextPage(int endPage, int totalPage) {
		if (endPage == totalPage) {
			return 0;
		}
		return endPage + 1;
	}
	
	public static int getPreviousPage(int startPage) {
		return startPage - 1;
	}
	
	private static int getEndPage(int startPage, int limitPage, int totalPage) {
		int endPage = startPage + limitPage - 1;
		if (endPage > totalPage) {
			return totalPage;
		}
		return endPage;
	}
	
	private static int getStartPage(int currentGroupPage, int limitPage) {
		return currentGroupPage * limitPage - limitPage + 1;
	}
	
	public static int getTotalPage(int totalUser, int limitPage) {
		return (int) Math.ceil((double) totalUser / limitPage);
	}
	
	private static int getCurrentGroupPage(int currentPage, int limitPage) {
		return (int) Math.ceil((double) currentPage / limitPage);
	}
	
	public static int getOffset(int currentPage) {
		return (currentPage - 1) * Constant.LIMIT_USER;
	}
	
	public static String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	public static boolean checkStringEmptyOrNull(String data) {
		if (data == null || data.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static boolean regexInsuranceNumber(String insuranceNumber) {
		return insuranceNumber.matches("^(?=.*\\d)(?=.*\\w).{10}$");
	}
	
	public static boolean checkMaxLength(String userFullName) {
		return userFullName.length() <= 255;
	}
	
	public static boolean checkRangeLength(String userName) {
		return 5 < userName.length() && userName.length() <= 15;
	}
	
	public static boolean regexPassword(String password) {
		return password.matches("^(?=.*\\d)(?=.*\\w).{6,15}$");
	}
	
	public static boolean checkValidInsuranceDate(String insuranceStartDate, String insuranceEndDate)
			throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(insuranceStartDate).before(dateFormat.parse(insuranceEndDate));
	}
	
	public static boolean checkValidBirthdate(String birthDate, Date currentDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.parse(birthDate).after(currentDate);
	}
	
	public static boolean regexEmail(String companyEmail) {
		return companyEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}
	
	public static boolean regexTelephone(String companyTelephone) {
		return companyTelephone.matches("0[0-9]{10,11}");
	}
	
	public static boolean checkFormatDate(String date) {
		try {
			new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException par) {
			return false;
		}
		return true;
	}
	
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
	 * @param str là chuỗi ký tự
	 * @return chuỗi ký tự không dấu
	 */
	public static String unAccent(String str) {
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d");
	}
}
