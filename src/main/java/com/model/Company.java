package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_company")
public class Company {
	
	@Id
	@GeneratedValue()
	@Column(name = "company_internal_id")
	private int companyInternalId;

	@Column(name = "company_name")
	private String companyName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	
	public Company() {
	}
	
	public Company(String companyName, String address, String email, String telephone) {
		this.companyName = companyName;
		this.address = address;
		this.email = email;
		this.telephone = telephone;
	}
	
	public int getCompanyInternalId() {
		return companyInternalId;
	}
	
	public void setCompanyInternalId(int companyInternalId) {
		this.companyInternalId = companyInternalId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
