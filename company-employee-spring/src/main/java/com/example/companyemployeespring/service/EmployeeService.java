package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    void deleteById(int id);

    void addEmploy(Employee employee, MultipartFile multipartFile) throws IOException;

    Optional<Employee> findByEmail(String email);

    List<Employee> findByCompanyId(int companyId, int currentId);
}
