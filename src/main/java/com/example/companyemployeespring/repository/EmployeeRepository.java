package com.example.companyemployeespring.repository;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Transactional
    List<Employee> deleteEmployeeByCompany(Company company);

    List<Employee> findAllByCompany(Company company);

    Optional<Employee> findByEmail(String email);
}
