package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.exceptions.AuthFailException;
import com.timibolaji.ecommerce.api.model.AuthenticationToken;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.repository.AuthenticationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    public User getUser(String token){
        final AuthenticationToken authenticationToken = authenticationTokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthFailException{
        //null check
        if(Objects.isNull(token)){
            //throw an exception
            throw new AuthFailException("Missing Token");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthFailException("Invalid Token");
        }
    }
}
