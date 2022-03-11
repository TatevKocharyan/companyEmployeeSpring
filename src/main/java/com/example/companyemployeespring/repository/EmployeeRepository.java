package com.example.companyemployeespring.repository;

import com.example.companyemployeespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
