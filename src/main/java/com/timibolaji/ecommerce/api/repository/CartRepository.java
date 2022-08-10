package com.timibolaji.ecommerce.api.repository;

import com.timibolaji.ecommerce.api.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
