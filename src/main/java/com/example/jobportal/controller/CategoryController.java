package com.example.jobportal.controller;

import com.example.jobportal.entity.Category;
import com.example.jobportal.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/admin")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Create
    @PostMapping("/category")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createcategory(category);
    }

    //Read
    @GetMapping("/category")
    public List<Category> getAllCategories()
    {
        return categoryService.getAllCategory();
    }
}
