package com.example.jobportal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String description;
    private String location;
    private String website;
    private LocalDateTime startDate;
    @OneToOne
    @JoinColumn(name = "employer_id")
    private User employer;
    @PrePersist
    public void prePersist() {
        startDate = LocalDateTime.now();
    }
}
