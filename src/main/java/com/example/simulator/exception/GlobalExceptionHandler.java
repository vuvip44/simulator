package com.example.simulator.exception;

import com.example.simulator.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> validationError(MethodArgumentNotValidException ex){
        BindingResult result=ex.getBindingResult();
        final List<FieldError> fieldErrors=result.getFieldErrors();

        ApiResponse<Object> res=new ApiResponse<>();
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setMessage(ex.getBody().getDetail());

        List<String> errors= new ArrayList<>();
        for(FieldError f:fieldErrors){
            errors.add(f.getDefaultMessage());
        }

        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}
