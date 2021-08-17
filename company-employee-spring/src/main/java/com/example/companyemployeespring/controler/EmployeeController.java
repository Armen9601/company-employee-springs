package com.example.companyemployeespring.controler;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employee")
    public String employees(ModelMap modelMap) {
        List<Employee> allEmployees = employeeRepository.findAll();
        modelMap.addAttribute("allEmployees", allEmployees);
        return "employee";
    }

    @GetMapping("/addEmploye")
    public String addCompany(ModelMap modelMap) {
        List<Company> all = companyRepository.findAll();
        modelMap.addAttribute("all", all);
        return "addEmployee";
    }

    @PostMapping("/addemployees")
    public String addEmpl(@ModelAttribute Employee employee,@RequestParam(name = "company") Company company) {

      employee.setCompany(company);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/empdelete")
    public String deleteEmploye(@RequestParam int id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }
}
