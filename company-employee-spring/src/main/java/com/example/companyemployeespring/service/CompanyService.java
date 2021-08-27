package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Company;

import java.util.List;

public interface CompanyService {

List<Company> findAll();

void deleteById(int id);

void save(Company company);

}
