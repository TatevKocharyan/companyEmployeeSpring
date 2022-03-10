package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addCompany(
            @RequestParam("name") String name,
            @RequestParam("size") int size,
            @RequestParam("address") String address
    ) {
        Company company = Company.builder()
                .name(name)
                .size(size)
                .address(address)
                .build();
        companyRepository.save(company);
        return "redirect:companies";
    }

    @GetMapping("/deletecompany/{id}")
    public String deleteCompany(@PathVariable int id) {
        companyRepository.deleteById(id);
        return "redirect:/companies";
    }
}
