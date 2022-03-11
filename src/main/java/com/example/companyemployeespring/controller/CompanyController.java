package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/companies")
    public String companies(ModelMap map) {
        List<Company> companies = companyRepository.findAll();
        map.addAttribute("companies", companies);
        return ("companies");
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company) {
        companyRepository.save(company);
        return "redirect:companies";
    }

    @GetMapping("/deletecompany/{id}")
    public String deleteCompany(@PathVariable int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }
}
