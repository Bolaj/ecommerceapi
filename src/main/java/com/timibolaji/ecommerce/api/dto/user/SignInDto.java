package com.timibolaji.ecommerce.api.dto.user;

import lombok.Data;


@Data
public class SignInDto {
    private String email;
    private String password;

}
