package com.example.simulator.exception;

import com.example.simulator.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SImRuntime.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleSimulatedRunTimeException(SImRuntime ex) {
        return new ApiResponse<>(500, ex.getMessage(), null);
    }

    @ExceptionHandler(SimFile.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<?> handleSimulatedFileException(SimFile ex) {
        return new ApiResponse<>(500, ex.getMessage(), null);
    }
}
