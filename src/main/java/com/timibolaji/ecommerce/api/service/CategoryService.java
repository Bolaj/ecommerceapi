package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.model.Category;
import com.timibolaji.ecommerce.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public void createCategory(Category category)
    {
        categoryRepository.save(category);
    }
}
