package com.example.jobportal.repository;

import com.example.jobportal.entity.Company;
import com.example.jobportal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmployer(User employer);
}
