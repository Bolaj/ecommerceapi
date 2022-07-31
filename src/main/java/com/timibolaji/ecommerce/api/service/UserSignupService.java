package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.dto.ResponseDto;
import com.timibolaji.ecommerce.api.dto.user.SignInDto;
import com.timibolaji.ecommerce.api.dto.user.SignInResponseDto;
import com.timibolaji.ecommerce.api.dto.user.SignupDto;
import com.timibolaji.ecommerce.api.exceptions.AuthFailException;
import com.timibolaji.ecommerce.api.exceptions.CustomerException;
import com.timibolaji.ecommerce.api.model.AuthenticationToken;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserSignupService {

    @Autowired
    AuthenticationTokenService authenticationTokenService;
    @Autowired
    UserRepository userRepository;
    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        //check if user is already present
        if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))){
            throw new CustomerException("User already exist" );
        }
        //hash the password
        String encryptedPassword = signupDto.getPassword();
        try{
            encryptedPassword = hashPassword(signupDto.getPassword());
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        //save the user
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedPassword);
        userRepository.save(user);
        //create the token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationTokenService.saveConfirmationToken(authenticationToken);

        //return response
        ResponseDto responseDto = new ResponseDto("success","Profile Created");
        return  responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;

    }

    public SignInResponseDto signIn(SignInDto signInDto) {
        //find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());
        if(Objects.isNull(user)){
            throw new AuthFailException("User Not Found");

        }
        //hash the password
        try {
            if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthFailException("Password Does Not Match, Try Again");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //compare the password in the db
        //if password matches,
        AuthenticationToken token = authenticationTokenService.getToken(user);
        //retrieve token and
        if(Objects.isNull(token)){
            throw new CustomerException("Token Not Found");
        }
        // return response
        return new SignInResponseDto("success", token.getToken());
    }
}
