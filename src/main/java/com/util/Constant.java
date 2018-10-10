package com.util;

public class Constant {
	
	public static final int LIMIT_USER = 5;
	
	public static final int LIMIT_PAGE = 5;
	
	public static final int DEFAULT_OFFSET = 0;
	
	
	public enum GENDER {
		MALE(1, "Nam"),
		FEMALE(2, "Nữ");
		
		private final int code;
		
		private final String gender;
		
		
		GENDER(int code, String gender) {
			this.code = code;
			this.gender = gender;
		}
		
		public int getCode() {
			return code;
		}
		
		public String getGender() {
			return gender;
		}
	}
}
