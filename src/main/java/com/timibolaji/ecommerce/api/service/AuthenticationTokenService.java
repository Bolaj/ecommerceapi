package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.model.AuthenticationToken;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    AuthenticationTokenRepository authenticationTokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        authenticationTokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return authenticationTokenRepository.findByUser(user);
    }
}
