package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.dto.cart.AddToCartDto;
import com.timibolaji.ecommerce.api.model.Cart;
import com.timibolaji.ecommerce.api.model.Product;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class CartService {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartRepository cartRepository;


    public void addToCart(AddToCartDto addToCartDto, User user) {
        //validate if product id is valid
       Product product = productService.findById(addToCartDto.getProduct_id());
       Cart cart = new Cart();
       cart.setProduct(product);
       cart.setUser(user);
       cart.setQuantity(addToCartDto.getQuantity());
       cart.setCreatedDate(new Date());

        //save the cart
        cartRepository.save(cart);
    }
}
