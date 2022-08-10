package com.timibolaji.ecommerce.api.repository;

import com.timibolaji.ecommerce.api.model.Cart;
import com.timibolaji.ecommerce.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
