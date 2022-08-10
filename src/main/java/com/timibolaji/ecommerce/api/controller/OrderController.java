package com.timibolaji.ecommerce.api.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.timibolaji.ecommerce.api.dto.checkout.CheckoutItemDto;
import com.timibolaji.ecommerce.api.dto.checkout.StripeResponse;
import com.timibolaji.ecommerce.api.service.AuthenticationTokenService;
import com.timibolaji.ecommerce.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private AuthenticationTokenService authenticationTokenService;
    @Autowired
    private OrderService orderService;

    //stripe session checkout api
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException
    {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);

    }

}
