package com.example.companyemployeespring.controller;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import lombok.Builder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Value("${companyEmployeeSpring.upload.path}")
    private String imagePath;


    @GetMapping("/employees")
    public String employees(ModelMap map) {
        List<Employee> employees = employeeRepository.findAll();
        List<Company> companies = companyRepository.findAll();
        map.addAttribute("employees", employees);
        map.addAttribute("companies", companies);
        return "employees";
    }


    @GetMapping("deleteemployee/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

    @PostMapping("/addemployee")
    public String addemployee(@ModelAttribute Employee employee,
                              @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imagePath + fileName);
            multipartFile.transferTo(file);
            employee.setPicUrl(fileName);
            employeeRepository.save(employee);
        }
        return "redirect:/employees";
    }


    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePath + picName);
        return IOUtils.toByteArray(inputStream);}


}
