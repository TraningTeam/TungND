package com.model;

import com.util.Common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "tbl_insurance")
public class Insurance {
	
	@Id
	@GeneratedValue
	@Column(name = "insurance_internal_id")
	private int insuranceInternalId;
	
	@Pattern(regexp = "[\\w]{10}", message = "Mã số thẻ bảo hiểm cần nhập 10 ký tự bao gồm cả chữ và số")
	@Column(name = "insurance_number")
	private String insuranceNumber;
	
	@Column(name = "insurance_start_date")
	private Date insuranceStartDate;
	
	@Column(name = "insurance_end_date")
	private Date insuranceEndDate;
	
	@Column(name = "place_of_register")
	private String placeOfRegister;
	
	
	public Insurance() {
	}
	
	public Insurance(UserRegisterForm userRegisterForm) throws ParseException {
		this.insuranceNumber = userRegisterForm.getInsuranceNumber();
		this.placeOfRegister = userRegisterForm.getPlaceOfRegister();
		this.insuranceStartDate = Common.convertStringToDate(userRegisterForm.getInsuranceStartDate());
		this.insuranceEndDate = Common.convertStringToDate(userRegisterForm.getInsuranceEndDate());
	}
	
	public int getInsuranceInternalId() {
		return insuranceInternalId;
	}
	
	public void setInsuranceInternalId(int insuranceInternalId) {
		this.insuranceInternalId = insuranceInternalId;
	}
	
	public String getInsuranceNumber() {
		return insuranceNumber;
	}
	
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	
	public Date getInsuranceStartDate() {
		return insuranceStartDate;
	}
	
	public void setInsuranceStartDate(Date insuranceStartDate) {
		this.insuranceStartDate = insuranceStartDate;
	}
	
	public Date getInsuranceEndDate() {
		return insuranceEndDate;
	}
	
	public void setInsuranceEndDate(Date insuranceEndDate) {
		this.insuranceEndDate = insuranceEndDate;
	}
	
	public String getPlaceOfRegister() {
		return placeOfRegister;
	}
	
	public void setPlaceOfRegister(String placeOfRegister) {
		this.placeOfRegister = placeOfRegister;
	}
}
