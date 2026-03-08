package com.example.jobportal.mapper;

import com.example.jobportal.dto.ApplicationRequestDTO;
import com.example.jobportal.dto.ApplicationResponseDTO;
import com.example.jobportal.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    Application toEntity(ApplicationRequestDTO applicationRequestDTO);
    @Mapping(source = "job.title", target = "jobTitle")
    @Mapping(source = "jobSeeker.name", target = "applicantName")
    ApplicationResponseDTO toDTO(Application application);
}
