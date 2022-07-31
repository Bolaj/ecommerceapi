package com.timibolaji.ecommerce.api.controller;

import com.timibolaji.ecommerce.api.dto.ResponseDto;
import com.timibolaji.ecommerce.api.dto.user.SignInDto;
import com.timibolaji.ecommerce.api.dto.user.SignInResponseDto;
import com.timibolaji.ecommerce.api.dto.user.SignupDto;
import com.timibolaji.ecommerce.api.service.UserSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserSignupService userSignupService;

    //signUp
    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignupDto signupDto)
    {
        return userSignupService.signUp(signupDto);
    }
    //signIn
    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) {
        return userSignupService.signIn(signInDto);

    }
}
