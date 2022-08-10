package com.timibolaji.ecommerce.api.dto.cart;

import com.timibolaji.ecommerce.api.model.Cart;
import com.timibolaji.ecommerce.api.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItemDto(Cart cart){
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());

    }

}
