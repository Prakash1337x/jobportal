package com.example.jobportal.dto;

import com.example.jobportal.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyCreateDTO {
    @JsonProperty("companyname")
    private String companyName;
    private String description;
    private String location;
    private String website;
    @JsonProperty("employerid")
    private Long employerId;
}
