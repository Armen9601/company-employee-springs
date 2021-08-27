package com.example.companyemployeespring.controler;


import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies")
    public String companies(ModelMap modelMap) {
        List<Company> allCompanies = companyService.findAll();
        modelMap.addAttribute("allCompanies", allCompanies);
        return "company";
    }

    @GetMapping("/addCompany")
    public String addCompany() {

        return "addCompany";
    }

    @PostMapping("/add")
    public String addCompanies(@ModelAttribute Company company) {
        companyService.save(company);
        return "redirect:/companies";
    }

    @GetMapping("/delete")
    public String deleteCompany(@RequestParam int id) {
        companyService.deleteById(id);
        return "redirect:/companies";
    }

}
