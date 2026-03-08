package com.example.jobportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.mapstruct.Mapping;

@Data
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Double salary;
    private String location;
    private int experience;
    @JsonProperty("jobtype")
    private String jobType;
    @JsonProperty("companyname")
    private String companyName;
}
