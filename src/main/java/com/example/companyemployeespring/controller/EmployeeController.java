package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/employees")
    public String employees(ModelMap map) {
        List<Employee> employees = employeeRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        map.addAttribute("employees", employees);
        map.addAttribute("companies", companies);
        return "employees";
    }


    @GetMapping("deleteemployee/{id}")
    public String deleteemployee(@PathVariable int id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

    @PostMapping("/addemployee")
    public String addemployee(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("email") String email,
            @RequestParam("phonenumber") String phoneNumber,
            @RequestParam("salary") String salary,
            @RequestParam("position") String position,
            @RequestParam("company.id") Company company) {
        Employee employee = Employee.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phoneNumber(phoneNumber)
                .salary(salary)
                .position(position)
                .company(company)
                .build();
        employeeRepository.save(employee);
        return "redirect:/employees";
    }


}
