package com.example.jobportal.dto;

import com.example.jobportal.entity.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicationResponseDTO {
    private Long id;

    @JsonProperty("jobtitle")
    private Long jobTitle;

    @JsonProperty("applicantname")
    private String applicantName;

    private ApplicationStatus status;

    @JsonProperty("applieddate")
    private LocalDateTime appliedDate;
}
