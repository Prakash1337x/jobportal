package com.example.jobportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CompanyResponseDTO {
    @JsonProperty("companyname")
    private String companyName;
    private String description;
    private String location;
    private String website;
    @JsonProperty("employername")
    private String employerName;
}
