package com.util;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
