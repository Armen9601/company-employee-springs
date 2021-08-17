package com.example.companyemployeespring.controler;


import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companies(ModelMap modelMap) {
        List<Company> allCompanies = companyRepository.findAll();
        modelMap.addAttribute("allCompanies", allCompanies);
        return "company";
    }

    @GetMapping("/addCompany")
    public String addCompany() {

        return "addCompany";
    }

    @PostMapping("/add")
    public String addCompanies(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/delete")
    public String deleteCompany(@RequestParam int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }

}
