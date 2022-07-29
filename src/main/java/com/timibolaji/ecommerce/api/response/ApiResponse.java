package com.timibolaji.ecommerce.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class ApiResponse {
    private final boolean success;
    private final String message;

    public  String getTimeStamps(){
        return LocalDateTime.now().toString();
    }
}
