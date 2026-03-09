package com.example.jobportal.service;

import com.example.jobportal.dto.CompanyCreateDTO;
import com.example.jobportal.dto.CompanyResponseDTO;
import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import com.example.jobportal.mapper.ComapnyMapper;
import com.example.jobportal.repository.CompanyRepository;
import com.example.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final ComapnyMapper comapnyMapper;

    public CompanyService(CompanyRepository companyRepository, UserRepository userRepository,
                          ComapnyMapper comapnyMapper) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.comapnyMapper = comapnyMapper;
    }

    //Create company
    public CompanyResponseDTO createCompany(CompanyCreateDTO dto) {
        User employer = userRepository.findById(dto.getEmployerId())
                .orElseThrow(()->new RuntimeException("Employer not found"));
        Company company = comapnyMapper.toEntity(dto);
        company.setEmployer(employer);
        Company saved = companyRepository.save(company);
        return comapnyMapper.toDTO(saved);
    }

    //View Company
    public CompanyResponseDTO getCompanyByEmployerId(Long employeeId) {
        User employer = userRepository.findById(employeeId)
                .orElseThrow(()->new RuntimeException("Employee not found"));
        Company company = companyRepository.findByEmployer(employer)
                .orElseThrow(()->new RuntimeException("Company not found"));
        return comapnyMapper.toDTO(company);
    }
}
