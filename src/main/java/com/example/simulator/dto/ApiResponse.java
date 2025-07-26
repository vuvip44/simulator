package com.example.simulator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;


    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public ApiResponse(int status, String message,T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
