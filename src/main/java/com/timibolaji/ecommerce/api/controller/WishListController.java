package com.timibolaji.ecommerce.api.controller;

import com.timibolaji.ecommerce.api.dto.ProductDto;
import com.timibolaji.ecommerce.api.model.Product;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.model.Wishlist;
import com.timibolaji.ecommerce.api.response.ApiResponse;
import com.timibolaji.ecommerce.api.service.AuthenticationTokenService;
import com.timibolaji.ecommerce.api.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    @Autowired
    WishListService wishListService;
    @Autowired
    AuthenticationTokenService authenticationTokenService;

    //save prdocuts in wishlist
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token){

        //authenticate the user
         authenticationTokenService.authenticate(token);
        //find the user
        User user = authenticationTokenService.getUser(token);
        //save the item in wishlist
        Wishlist wishlist = new Wishlist(user, product);
        wishListService.createWishList(wishlist);
        ApiResponse apiResponse = new ApiResponse(true,"Added to Wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }


    //get all wishlists
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token){
        //authenticate the user
        authenticationTokenService.authenticate(token);
        //find the user
        User user = authenticationTokenService.getUser(token);
        List<ProductDto> wishListForUser =  wishListService.getWishListForUser(user);
        return new ResponseEntity<>(wishListForUser, HttpStatus.OK);

    }


}
