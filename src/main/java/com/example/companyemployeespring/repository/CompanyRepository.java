package com.example.companyemployeespring.repository;

import com.example.companyemployeespring.entity.Company;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CompanyRepository extends CrudRepository<Company,Integer> {
    List<Company> findAll();
}
