package com.example.companyemployeespring.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "redirect:/login";
    }

    @GetMapping("/accessDenied")
    public String acsses() {
        return "403";
    }






}
