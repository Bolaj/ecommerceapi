package com.timibolaji.ecommerce.api.repository;

import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);

}
