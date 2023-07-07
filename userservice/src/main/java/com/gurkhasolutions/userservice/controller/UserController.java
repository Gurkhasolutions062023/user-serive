package com.gurkhasolutions.userservice.controller;


import com.gurkhasolutions.userservice.dto.ApiResponse;
import com.gurkhasolutions.userservice.dto.UserDto;
import com.gurkhasolutions.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {


    // stater validation

    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public String myName(){
        return "This is spring boot application";
    }

    @PostMapping("/user")
    public ApiResponse createUser(@Valid @RequestBody UserDto userDto){
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        if(fieldErrors.size()>0){
//
//        }



//        ApiResponse<User> user= new ApiResponse<>();
//        ApiResponse<String> str= new ApiResponse<>();
        return userService.createUser(userDto);
    }

//    Get method
    // path varialbe and request param ?id=1&shhhg=uu
    // {id}
    @GetMapping("/user/{userId}")
    public ApiResponse getUserById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    // Get All User
    @GetMapping("/users")
    public ApiResponse getAllUsers(){
       return userService.finAllUsers();
    }


    // for Delete
    @DeleteMapping("/user/{userId}")
    public ApiResponse deleteUser(@PathVariable Long userId){
        return userService.deleteUserById(userId);
    }

    //updating the user
    @PutMapping("/user/{userId}")
    public Object updateUser(@PathVariable Long userId,@Valid @RequestBody UserDto userDto){
        return userService.updateUser(userId,userDto);

    }



// get user by email
    //http://localhost:8080/api/user/email?e=khagen@gmail.com&firstName=khagen
    @GetMapping("/user/email")
    public ApiResponse getUserByEmail(@RequestParam(name = "e") String email){
        return userService.findUserByEmail(email);


    }

    @GetMapping("/user/role")
    public ApiResponse getUsersByRole(@RequestParam(name = "r") String role){
        return userService.getUsersByRole(role);
    }



}
