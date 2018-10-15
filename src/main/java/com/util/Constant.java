package com.util;

public class Constant {

	/** Max of user in a page */
	public static final int LIMIT_USER = 5;

	/** Max of page in list paging */
	public static final int LIMIT_PAGE = 5;

	/** Format of date */
	public static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

    /** Url add insurance */
    public static final String URL_ADD_INSURANCE ="/addInsurance";

    /** Url add insurance */
    public static final String URL_LIST_INSURANCE ="/listInsurance";

    /** Url export */
    public static final String URL_EXPORT = "/export";

    /** Attribute of company list */
    public static final String ATTRIBUTE_COMPANY_LIST = "company_list";

    /** Attribute of company */
    public static final String ATTRIBUTE_COMPANY = "company";

    /** Attribute of company id selected */
    public static final String ATTRIBUTE_COMPANY_ID_SELECTED = "company_id_selected";

	/** Attribute of sort type */
	public static final String ATTRIBUTE_SORT_TYPE = "sort_type";

	/** Attribute of current page */
	public static final String ATTRIBUTE_CURRENT_PAGE = "current_page";

	/** Attribute of user list */
	public static final String ATTRIBUTE_USER_LIST = "user_list";

	/** Attribute of page list */
	public static final String ATTRIBUTE_PAGE_LIST = "page_list";

	/** Attribute of next page */
	public static final String ATTRIBUTE_NEXT_PAGE = "next_page";

	/** Attribute of total page */
	public static final String ATTRIBUTE_TOTAL_PAGE = "total_page";

	/** Attribute of previous page */
	public static final String ATTRIBUTE_PREVIOUS_PAGE = "previous_page";

	/** Attribute of total user */
	public static final String ATTRIBUTE_TOTAL_USER = "total_user";

    /** Attribute of user full name */
    public static final String ATTRIBUTE_USER_FULL_NAME = "user_full_name";

    /** Attribute of insurance number */
    public static final String ATTRIBUTE_INSURANCE_NUMBER = "insurance_number";

    /** Attribute of place of register */
    public static final String ATTRIBUTE_PLACE_OF_REGISTER = "place_of_register";

    /** Attribute of status code */
    public static final String ATTRIBUTE_STATUS_CODE = "status_code";

    /** Attribute of message */
    public static final String ATTRIBUTE_MESSAGE = "message";

    /** View of list insurance */
    public static final String VIEW_LIST_INSURANCE = "list_insurance";

    /** View of add insurance */
    public static final String VIEW_ADD_INSURANCE = "add_insurance";

    /** Flag of exist company */
    public static final String FLAG_EXIST_COMPANY = "1";

    /** Flag of exist company */
    public static final String FLAG_NEW_COMPANY = "2";


    public enum GENDER {
		/** MALE */
		MALE('1', "Nam"),
		/** FEMALE */
		FEMALE('2', "Nữ");

		private final char code;

		private final String gender;


		GENDER(char code, String gender) {
			this.code = code;
			this.gender = gender;
		}

		public char getCode() {
			return code;
		}

		public String getGender() {
			return gender;
		}
	}

	public enum ACTION_SEARCH_TYPE {
		/** SORT */
		SORT,
		/** PAGING */
		PAGING,
		/** BACK */
		BACK;
	}
}
