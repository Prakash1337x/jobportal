package com.example.jobportal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApplicationRequestDTO {
    @JsonProperty("jobid")
    private Long jobId;

    @JsonProperty("userid")
    private Long userId;

    @JsonProperty("resumeurl")
    private String resumeUrl;

    @JsonProperty("coverletter")
    private String coverLetter;
}
