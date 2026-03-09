package com.example.jobportal.mapper;

import com.example.jobportal.dto.JobCreateDTO;
import com.example.jobportal.dto.JobResponseDTO;
import com.example.jobportal.entity.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job toEntity(JobCreateDTO jobCreateDTO);
    @Mapping(source = "company.companyName", target = "companyName")
    JobResponseDTO toDTO(Job job);
    void updateJob(Job job, @MappingTarget JobCreateDTO jobCreateDTO);
}
