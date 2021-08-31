package com.example.companyemployeespring.controler;


import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.entity.Message;
import com.example.companyemployeespring.security.CurrentUser;
import com.example.companyemployeespring.service.EmployeeService;
import com.example.companyemployeespring.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller

public class MessageController {

    public MessageController(MessageService messageService, EmployeeService employeeService) {
        this.messageService = messageService;
        this.employeeService = employeeService;
    }

    private final MessageService messageService;
    private final EmployeeService employeeService;

    @GetMapping("/messages")
    public String messageWith(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam(name = "id") Employee employee) {
        List<Employee> byCompanyId = employeeService.findByCompanyId(currentUser.getEmployee().getCompany().getId(), currentUser.getEmployee().getId());
        modelMap.addAttribute("byCompanyId", byCompanyId);
        modelMap.addAttribute("currentUser", currentUser.getEmployee());
        modelMap.addAttribute("toEmployee", employee);
        List<Message> byToIdAndFromId = messageService.findByFromIdAndToIdOrToIdAndFromId(currentUser.getEmployee().getId(), employee.getId());
        modelMap.addAttribute("byToIdAndFromId", byToIdAndFromId);
        return "messegeWith";
    }

    @GetMapping("/message")
    public String message(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Employee> byCompanyId = employeeService.findByCompanyId(currentUser.getEmployee().getCompany().getId(), currentUser.getEmployee().getId());
        modelMap.addAttribute("byCompanyId", byCompanyId);
        modelMap.addAttribute("employee", currentUser.getEmployee());
        return "message";
    }

//    @GetMapping("/companies")
//    public String companies(ModelMap modelMap) {
//        List<Company> allCompanies = companyService.findAll();
//        modelMap.addAttribute("allCompanies", allCompanies);
//        return "company";
//    }
//
//    @GetMapping("/addCompany")
//    public String addCompany() {
//
//        return "addCompany";
//    }

    @PostMapping("/send")
    public String sendMessage(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam Employee toEmployee, @RequestParam(name = "message") String text) {
        Message message = new Message();
        message.setFrom(currentUser.getEmployee());
        message.setTo(toEmployee);
        message.setMessage(text);
        message.setCreatedDate(LocalDate.now());
        messageService.save(message);
        return "redirect:/messages?id=" + toEmployee.getId();
    }

//    @GetMapping("/delete")
//    public String deleteCompany(@RequestParam int id) {
//        messageService.deleteById(id);
//        return "redirect:/messages";
//    }

}
