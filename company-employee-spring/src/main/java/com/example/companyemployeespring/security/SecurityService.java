package com.example.companyemployeespring.security;

import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Employee> byEmail = employeeService.findByEmail(s);
        if (!byEmail.isPresent()) {
            throw new UsernameNotFoundException("User with " + s + " use    rname does not exists");
        }

        return new CurrentUser(byEmail.get());
    }
}
