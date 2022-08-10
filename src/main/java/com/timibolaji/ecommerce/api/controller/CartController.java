package com.timibolaji.ecommerce.api.controller;

import com.timibolaji.ecommerce.api.dto.cart.AddToCartDto;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.response.ApiResponse;
import com.timibolaji.ecommerce.api.service.AuthenticationTokenService;
import com.timibolaji.ecommerce.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @PostMapping("/add")
    //post cart api
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token)
    {

        //authenticate the user
        authenticationTokenService.authenticate(token);
        //find the user
        User user = authenticationTokenService.getUser(token);

        cartService.addToCart(addToCartDto,user);
        return new ResponseEntity<>(new ApiResponse(true,"Added To Cart"), HttpStatus.CREATED);

    }
    //get all cart items for a user
    //delete cart item for a user

}
