package com.example.jobportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobCreateDTO {
    private String title;
    private String description;
    private Double salary;
    private String location;
    private int experience;
    @JsonProperty("jobtype")
    private String jobType;
    @JsonProperty("companyid")
    private Long companyId;
    @JsonProperty("categoryid")
    private Long categoryId;
}
