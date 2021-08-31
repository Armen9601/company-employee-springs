package com.example.companyemployeespring.repository;


import com.example.companyemployeespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);

    List<Employee> findByCompanyIdAndIdNot(int companyId, int currentId);
}
