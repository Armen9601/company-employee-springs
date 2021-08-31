package com.example.companyemployeespring.service.impl;

import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.EmployeeRepository;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;


    @Value("C:\\Java2021\\Spring\\company-employee-spring\\src\\main\\resources\\static\\assets\\img\\employee")
    private String uploadDir;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void addEmploy(Employee employee, MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String picUrl = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadDir + File.separator + picUrl));
            employee.setPicUrl(picUrl);
        }

        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> findByCompanyId(int companyId, int currentId) {
        return employeeRepository.findByCompanyIdAndIdNot(companyId, currentId);
    }
}
