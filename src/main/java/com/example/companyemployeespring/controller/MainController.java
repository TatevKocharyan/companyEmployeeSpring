package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/")
    public String main() {
        return "main";
    }


}
