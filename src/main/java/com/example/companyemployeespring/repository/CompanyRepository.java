package com.example.companyemployeespring.repository;

import com.example.companyemployeespring.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

}
