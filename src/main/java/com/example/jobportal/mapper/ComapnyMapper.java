package com.example.jobportal.mapper;

import com.example.jobportal.dto.CompanyCreateDTO;
import com.example.jobportal.dto.CompanyResponseDTO;
import com.example.jobportal.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComapnyMapper {
    Company toEntity(CompanyCreateDTO companyCreateDTO);
    @Mapping(source = "employer.name", target = "employerName")
    CompanyResponseDTO toDTO(Company company);
}
