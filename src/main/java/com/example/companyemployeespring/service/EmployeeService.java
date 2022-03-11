package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.entity.Employee;
import com.example.companyemployeespring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service

public class EmployeeService {
    @Value("${myImages.upload.path}")
    private String imagePath;

    private final EmployeeRepository employeeRepository;

    public void deleteEmployeeByCompany(Company company) {
        employeeRepository.deleteEmployeeByCompany(company);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee addEmployee(Employee employee, MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imagePath + fileName);
            multipartFile.transferTo(file);
            employee.setPicUrl(fileName);
        }
        employeeRepository.save(employee);
        return employee;
    }
}
