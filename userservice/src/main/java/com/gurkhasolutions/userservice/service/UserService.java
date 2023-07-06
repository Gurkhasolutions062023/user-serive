package com.gurkhasolutions.userservice.service;


import com.gurkhasolutions.userservice.dto.UserDto;
import com.gurkhasolutions.userservice.model.User;
import com.gurkhasolutions.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Object createUser(UserDto userDto) {

        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if(!userByEmail.isPresent()){
            User user= User.builder()
                    .email(userDto.getEmail())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .role(userDto.getRole())
                    .build();
            return userRepository.save(user);
        }

        Map<String, String> errors= new HashMap<>();
        errors.put("message","User with email "+userDto.getEmail()+" already exist.");
        errors.put("statusCode","400");
        return errors;



    }

    public Object findUserById(Long userId) {
        Optional<User> userById = userRepository.findById(userId);
        if(userById.isPresent()){
            return userById;
        }
        Map<String, String> errors= new HashMap<>();
        errors.put("message","User with id "+userId+" not Found");
        errors.put("statusCode","404");
        return errors;

    }

    public List<User> finAllUsers() {
//        userRepository.findAll().stream()
//               .filter(user -> user.getRole().equals("User"))
//               .collect(Collectors.toList());

        return userRepository.findAll();
    }

    public Map<String, String> deleteUserById(Long userId) {

        Optional<User> userById = userRepository.findById(userId);
        Map<String,String > resp= new HashMap<>();
        if(userById.isPresent()){
            userRepository.deleteById(userId);
            resp.put("message","User with id "+userId+" deleted successfully");
            resp.put("statusCode","200");
            return resp;
        }
        resp.put("message","User with id "+userId+" not found");
        resp.put("statusCode","404");
        return resp;

    }

    public Object updateUser(Long userId, UserDto userDto) {
        Optional<User> userById = userRepository.findById(userId);
        if(userById.isPresent()){
            userById.get().setEmail(userDto.getEmail());
            userById.get().setRole(userDto.getRole());
            userById.get().setFirstName(userDto.getFirstName());
            userById.get().setLastName(userDto.getLastName());
            return userRepository.save(userById.get());
        }
        Map<String, String> errors= new HashMap<>();
        errors.put("message","User with id "+userId+" not Found");
        errors.put("statusCode","404");
        return errors;

    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUsersByRole(String role) {
       return userRepository.findUserWithRole(role);
    }
}
