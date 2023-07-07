package com.gurkhasolutions.userservice.exception;


import com.gurkhasolutions.userservice.dto.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        ApiResponse apiResponse= new ApiResponse();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<String> errors= new ArrayList<>();
        for(FieldError error: fieldErrors){
            errors.add(error.getDefaultMessage());
        }
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrors(errors);
        apiResponse.setMessage("Errors found");
        return ResponseEntity.badRequest().body(apiResponse);


    }

    @ExceptionHandler({UserNotFoundException.class, EmailNotFountException.class})
    public ResponseEntity<ApiResponse> handleException(Exception ex){
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        apiResponse.setErrors(ex.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
