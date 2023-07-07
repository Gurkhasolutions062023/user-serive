package com.gurkhasolutions.userservice.dto;


import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserDto {

    @Email(message = "Email should be on proper format")
    private String email;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 3, max = 10, message = "First name should be between 3 to 10 character")
    //@Max(value = 10,message = "First Name should not be more than 10 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Role name cannot be blank")
   // @Pattern(regexp = "/^([^0-9]*)$/", message = "Number is not allowed in role")
    private String role;
}
