package com.controller;

import java.io.IOException;
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
	
	private static final String CSV_HEADER = "Danh sách thông tin thẻ bảo hiểm";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CompanyService companyService;
	
	
	@RequestMapping("/export")
	public void downloadCsvFile(HttpServletResponse response, HttpSession session) {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"my-csv-file.csv\"");
		PrintWriter printWriter = null;
		int companyIdSelected = (int) session.getAttribute("company_id_selected");
		String userFullName = (String) session.getAttribute("user_full_name");
		String insuranceNumber = (String) session.getAttribute("insurance_number");
		String placeOfRegister = (String) session.getAttribute("place_of_register");
		List<User> userList = userService.getUserDataToExport(companyIdSelected, userFullName, insuranceNumber,
				placeOfRegister);
		Company company = companyService.findCompanyById(companyIdSelected);
		try {
			printWriter = response.getWriter();
			printWriter.append(CSV_HEADER);
			printWriter.append("\n");
			printWriter.append("Tên công ty");
			printWriter.append(",");
			printWriter.append(company.getCompanyName());
			printWriter.append("\n");
			printWriter.append("Địa chỉ");
			printWriter.append(",");
			printWriter.append(company.getAddress());
			printWriter.append("\n");
			printWriter.append("Số điện thoại");
			printWriter.append(",");
			printWriter.append(company.getTelephone());
			printWriter.append("\n\n");
			printWriter.append("Họ và tên");
			printWriter.append(",");
			printWriter.append("Giới tính");
			printWriter.append(",");
			printWriter.append("Ngày sinh");
			printWriter.append(",");
			printWriter.append("Mã số thẻ bảo hiểm");
			printWriter.append(",");
			printWriter.append("Ngày bắt đầu");
			printWriter.append(",");
			printWriter.append("Ngày kết thúc");
			printWriter.append(",");
			printWriter.append("Nơi đăng ký KCB");
			printWriter.append("\n");
			for (User user : userList) {
				printWriter.append(user.getUserFullName());
				printWriter.append(",");
				if (user.getUserSexDivision() == Constant.GENDER.FEMALE.getCode()) {
					printWriter.append(Constant.GENDER.FEMALE.getGender());
				} else {
					printWriter.append(Constant.GENDER.MALE.getGender());
				}
				printWriter.append(",");
				printWriter.append(Common.formatDate(user.getBirthDate()));
				printWriter.append(",");
				printWriter.append(user.getInsurance().getInsuranceNumber());
				printWriter.append(",");
				printWriter.append(Common.formatDate(user.getInsurance().getInsuranceStartDate()));
				printWriter.append(",");
				printWriter.append(Common.formatDate(user.getInsurance().getInsuranceEndDate()));
				printWriter.append(",");
				printWriter.append(user.getInsurance().getPlaceOfRegister());
				printWriter.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			printWriter.flush();
			printWriter.close();
		}
	}
}
