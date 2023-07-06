package com.gurkhasolutions.userservice.dto;


import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String firstName;
    private String lastName;
    private String role;
}
