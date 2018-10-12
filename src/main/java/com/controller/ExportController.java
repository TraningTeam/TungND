package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.model.Company;
import com.model.User;
import com.service.CompanyService;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;

@Controller
public class ExportController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	
	@RequestMapping("/export")
	public void downloadCsvFile(HttpServletResponse response, HttpSession session) throws IOException {
		OutputStream outputStream = null;
		StringBuilder contents = new StringBuilder();
		int companyIdSelected;
		String userFullName = "";
		String insuranceNumber = "";
		String placeOfRegister = "";
		if (session.getAttribute("company_id_selected") == null) {
			List<Company> companyList = companyService.findAllCompany();
			companyIdSelected = companyList.get(0).getCompanyInternalId();
		} else {
			companyIdSelected = (int) session.getAttribute("company_id_selected");
			userFullName = (String) session.getAttribute("user_full_name");
			insuranceNumber = (String) session.getAttribute("insurance_number");
			placeOfRegister = (String) session.getAttribute("place_of_register");
		}
		List<User> userList = userService.getUserDataToExport(companyIdSelected, userFullName, insuranceNumber,
				placeOfRegister);
		Company company = companyService.findCompanyById(companyIdSelected);
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");
		outputStream = response.getOutputStream();
		contents.append("Danh sách thông tin thẻ bảo hiểm");
		contents.append("\n\n");
		contents.append("Tên công ty");
		contents.append(",");
		contents.append(company.getCompanyName());
		contents.append("\n");
		contents.append("Địa chỉ");
		contents.append(",");
		contents.append(company.getAddress());
		contents.append("\n");
		contents.append("Email");
		contents.append(",");
		if (Common.checkStringEmptyOrNull(company.getEmail()) == true) {
			contents.append(company.getEmail());
		}
		contents.append("\n");
		contents.append("Số điện thoại");
		contents.append(",");
		if (Common.checkStringEmptyOrNull(company.getTelephone()) == true) {
			contents.append(company.getTelephone());
		}
		contents.append("\n\n");
		contents.append("Họ và tên");
		contents.append(",");
		contents.append("Giới tính");
		contents.append(",");
		contents.append("Ngày sinh");
		contents.append(",");
		contents.append("Mã số thẻ bảo hiểm");
		contents.append(",");
		contents.append("Ngày bắt đầu");
		contents.append(",");
		contents.append("Ngày kết thúc");
		contents.append(",");
		contents.append("Nơi đăng ký KCB");
		contents.append("\n");
		for (User user : userList) {
			contents.append(user.getUserFullName());
			contents.append(",");
			if (user.getUserSexDivision() == Constant.GENDER.FEMALE.getCode()) {
				contents.append(Constant.GENDER.FEMALE.getGender());
			} else {
				contents.append(Constant.GENDER.MALE.getGender());
			}
			contents.append(",");
			if (user.getBirthDate() != null) {
				contents.append(Common.formatDate(user.getBirthDate()));
			}
			contents.append(",");
			contents.append(user.getInsurance().getInsuranceNumber());
			contents.append(",");
			contents.append(Common.formatDate(user.getInsurance().getInsuranceStartDate()));
			contents.append(",");
			contents.append(Common.formatDate(user.getInsurance().getInsuranceEndDate()));
			contents.append(",");
			contents.append(user.getInsurance().getPlaceOfRegister());
			contents.append("\n");
		}
		outputStream.write(239);
		outputStream.write(187);
		outputStream.write(191);
		outputStream.write(contents.toString().getBytes("UTF-8"));
		outputStream.flush();
		outputStream.close();
	}
}
