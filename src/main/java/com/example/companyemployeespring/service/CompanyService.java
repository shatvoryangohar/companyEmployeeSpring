package com.example.companyemployeespring.service;

import com.example.companyemployeespring.entity.Company;
import com.example.companyemployeespring.repository.CompanyRepository;
import com.example.companyemployeespring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

private final CompanyRepository companyRepository;
private final EmployeeRepository employeeRepository;


public List<Company> findAll(){
    return companyRepository.findAll();
}

public Company save(Company company){
    return companyRepository.save(company);
}

public void deleteById(int id){
    companyRepository.deleteById(id);
}

public Company findById(int id){
   return companyRepository.getById(id);
}

}
