package com.example.jobportal.service;

import com.example.jobportal.entity.Category;
import com.example.jobportal.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //create category
    public Category createcategory(Category category) {
        return categoryRepository.save(category);
    }

    // Manage category
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
