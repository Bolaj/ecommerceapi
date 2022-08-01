package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.dto.ProductDto;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.model.Wishlist;
import com.timibolaji.ecommerce.api.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    ProductService productService;

    public void createWishList(Wishlist wishlist) {
        wishListRepository.save(wishlist);
    }

    public List<ProductDto> getWishListForUser(User user) {
       final List<Wishlist> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
       List<ProductDto> productDtos = new ArrayList<>();
       for(Wishlist wishList:wishLists){
           productDtos.add(productService.getProductDto(wishList.getProduct()));
       }
       return productDtos;

    }
}
