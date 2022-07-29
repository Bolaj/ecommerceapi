package com.timibolaji.ecommerce.api.controller;

import com.timibolaji.ecommerce.api.model.Category;
import com.timibolaji.ecommerce.api.response.ApiResponse;
import com.timibolaji.ecommerce.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category)
    {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true,"Category Successfully Created"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<Category> listCategory()
    {
        return categoryService.listCategory();
    }
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false,"Category Not Found"), HttpStatus.NOT_FOUND);
        }

        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true,"Category Successfully Updated"), HttpStatus.OK);
    }
}
