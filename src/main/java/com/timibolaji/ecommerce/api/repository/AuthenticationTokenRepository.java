package com.timibolaji.ecommerce.api.repository;

import com.timibolaji.ecommerce.api.model.AuthenticationToken;
import com.timibolaji.ecommerce.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);

}
