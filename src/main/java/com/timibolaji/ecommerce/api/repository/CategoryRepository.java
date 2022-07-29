package com.timibolaji.ecommerce.api.repository;

import com.timibolaji.ecommerce.api.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
