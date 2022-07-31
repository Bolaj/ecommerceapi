package com.timibolaji.ecommerce.api.exceptions;

public class CustomerException extends IllegalArgumentException{
    public CustomerException(String msg){
        super(msg);
    }
}
