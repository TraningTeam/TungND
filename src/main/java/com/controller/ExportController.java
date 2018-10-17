package com.controller;

import com.model.Company;
import com.model.User;
import com.service.CompanyService;
import com.service.UserService;
import com.util.Common;
import com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class ExportController {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private Common common;

    /**
     * Export csv file containing the searched infor
     *
     * @param response object to get stream
     * @param session  object get data to find info
     * @throws IOException if
     */
    @RequestMapping(Constant.URL_EXPORT)
    public void downloadCsvFile(HttpServletResponse response, HttpSession session) throws IOException {
        OutputStream outputStream;
        StringBuilder contents = new StringBuilder();
        int companyIdSelected;
        String userFullName = "";
        String insuranceNumber = "";
        String placeOfRegister = "";
        if (session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED) == null) {
            List<Company> companyList = companyService.findAllCompany();
            companyIdSelected = companyList.get(0).getCompanyInternalId();
        } else {
            companyIdSelected = (int) session.getAttribute(Constant.ATTRIBUTE_COMPANY_ID_SELECTED);
            userFullName = (String) session.getAttribute(Constant.ATTRIBUTE_USER_FULL_NAME);
            insuranceNumber = (String) session.getAttribute(Constant.ATTRIBUTE_INSURANCE_NUMBER);
            placeOfRegister = (String) session.getAttribute(Constant.ATTRIBUTE_PLACE_OF_REGISTER);
        }
        List<User> userList = userService.findUserDataToExport(companyIdSelected, userFullName, insuranceNumber,
                placeOfRegister);
        Company company = companyService.findCompanyById(companyIdSelected);
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"insuranceList.csv\"");
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
        if (common.checkStringEmptyOrNull(company.getEmail())) {
            contents.append(company.getEmail());
        }
        contents.append("\n");
        contents.append("Số điện thoại");
        contents.append(",");
        if (common.checkStringEmptyOrNull(company.getTelephone())) {
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
            contents.append(user.getUserFullName() + "\t");
            contents.append(",");
            if (user.getUserSexDivision() == Constant.GENDER.FEMALE.getCode()) {
                contents.append(Constant.GENDER.FEMALE.getGender());
            } else {
                contents.append(Constant.GENDER.MALE.getGender());
            }
            contents.append(",");
            if (user.getBirthDate() != null) {
                contents.append(common.formatDate(user.getBirthDate()));
            }
            contents.append(",");
            contents.append(user.getInsurance().getInsuranceNumber());
            contents.append(",");
            contents.append(common.formatDate(user.getInsurance().getInsuranceStartDate()));
            contents.append(",");
            contents.append(common.formatDate(user.getInsurance().getInsuranceEndDate()));
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
