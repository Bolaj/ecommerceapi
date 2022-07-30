package com.timibolaji.ecommerce.api.repository;

import com.timibolaji.ecommerce.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
