package com.example.companyemployeespring.controler;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.CompanyService;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    private final CompanyService companyService;

    private String productImages = "C:\\Java2021\\Spring\\company-employee-spring\\src\\main\\resources\\static\\assets\\img\\employee\\";

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/e")
    public String employeepage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> byCompanyId = employeeService.findByCompanyId(currentUser.getEmployee().getCompany().getId(), currentUser.getEmployee().getId());
        modelMap.addAttribute("byCompanyId", byCompanyId);
        modelMap.addAttribute("employee", currentUser.getEmployee());
        return "empl";
    }

    @GetMapping("/employee")
    public String employees(ModelMap modelMap) {
        List<Employee> allEmployees = employeeService.findAll();
        modelMap.addAttribute("allEmployees", allEmployees);
        return "employee";
    }

    @GetMapping("/addEmploye")
    public String addEmployee(ModelMap modelMap) {
        List<Company> all = companyService.findAll();
        modelMap.addAttribute("all", all);
        return "addEmployee";
    }

    @PostMapping("/addemployees")
    public String addEmpl(@ModelAttribute Employee employee, @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeService.addEmploy(employee, multipartFile);
        return "redirect:/employee";
    }

    @GetMapping("/empdelete")
    public String deleteEmploye(@RequestParam int id) {
        employeeService.deleteById(id);
        return "redirect:/employee";
    }

    @PostMapping("/employee/page")
    public String employeePage(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        List<Employee> byCompanyId = employeeService.findByCompanyId(currentUser.getEmployee().getCompany().getId(), currentUser.getEmployee().getId());
        modelMap.addAttribute("employee", currentUser.getEmployee());
        modelMap.addAttribute("byCompanyId", byCompanyId);
        return "employePage";
    }

    @GetMapping("/productImage")
    void productImage(@RequestParam("productUrl") String productUrl, HttpServletResponse response) throws IOException {
        InputStream in = new FileInputStream(productImages + File.separator + productUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }
}
