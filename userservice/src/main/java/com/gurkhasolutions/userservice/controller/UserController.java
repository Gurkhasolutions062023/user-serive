package com.gurkhasolutions.userservice.controller;


import com.gurkhasolutions.userservice.dto.UserDto;
import com.gurkhasolutions.userservice.model.User;
import com.gurkhasolutions.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public String myName(){
        return "This is spring boot application";
    }

    @PostMapping("/user")
    public Object createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }

//    Get method
    // path varialbe and request param ?id=1&shhhg=uu
    // {id}
    @GetMapping("/user/{userId}")
    public Object getUserById(@PathVariable Long userId){
        return userService.findUserById(userId);
    }

    // Get All User
    @GetMapping("/users")
    public List<User> getAllUsers(){
       return userService.finAllUsers();
    }


    // for Delete
    @DeleteMapping("/user/{userId}")
    public Map<String, String> deleteUser(@PathVariable Long userId){
        return userService.deleteUserById(userId);
    }

    //updating the user
    @PutMapping("/user/{userId}")
    public Object updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        return userService.updateUser(userId,userDto);

    }



// get user by email
    //http://localhost:8080/api/user/email?e=khagen@gmail.com&firstName=khagen
    @GetMapping("/user/email")
    public User getUserByEmail(@RequestParam(name = "e") String email){
        return userService.findUserByEmail(email).get();


    }

    @GetMapping("/user/role")
    public List<User> getUsersByRole(@RequestParam(name = "r") String role){
        return userService.getUsersByRole(role);
    }



}
