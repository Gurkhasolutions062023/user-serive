package com.gurkhasolutions.userservice.dto;

import lombok.Data;

@Data
public class ApiResponse {

   // private T data1;
    private Object data;
    private Object errors;
    private int statusCode;
    private String message;
}
