package com.timibolaji.ecommerce.api.exceptions;

public class AuthFailException extends IllegalArgumentException{
    public AuthFailException (String msg){
        super(msg);
    }
}
