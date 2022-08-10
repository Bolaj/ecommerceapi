package com.timibolaji.ecommerce.api.service;

import com.timibolaji.ecommerce.api.dto.cart.AddToCartDto;
import com.timibolaji.ecommerce.api.dto.cart.CartDto;
import com.timibolaji.ecommerce.api.dto.cart.CartItemDto;
import com.timibolaji.ecommerce.api.exceptions.CustomerException;
import com.timibolaji.ecommerce.api.model.Cart;
import com.timibolaji.ecommerce.api.model.Product;
import com.timibolaji.ecommerce.api.model.User;
import com.timibolaji.ecommerce.api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    public CartDto listCartItems(User user) {
       final List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
       List<CartItemDto> cartItems = new ArrayList<>();
       double totalCost = 0;
       for(Cart cart: cartList){
           CartItemDto cartItemDto = new CartItemDto(cart);
           totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
           cartItems.add(cartItemDto);
       }
    
       CartDto cartDto = new CartDto();
       cartDto.setTotalCost(totalCost);
       cartDto.setCartItems(cartItems);
       return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if(optionalCart.isEmpty()){
            throw new CustomerException("Cart Item ID is Not Valid: " + cartItemId);
        }

        Cart cart = optionalCart.get();
        if(cart.getUser() != user){
            throw new CustomerException("Cart Item Does Not Belong To User: " +cartItemId);
        }
        cartRepository.delete(cart);
    }
}
