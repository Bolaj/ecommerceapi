package com.timibolaji.ecommerce.api.controller;

import com.timibolaji.ecommerce.api.model.Category;
import com.timibolaji.ecommerce.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/create")
    public String createCategory(@RequestBody Category category)
    {
        categoryService.createCategory(category);
        return "Successful";
    }
}
