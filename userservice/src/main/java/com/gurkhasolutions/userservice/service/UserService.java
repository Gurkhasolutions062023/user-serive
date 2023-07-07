package com.gurkhasolutions.userservice.service;


import com.gurkhasolutions.userservice.dto.ApiResponse;
import com.gurkhasolutions.userservice.dto.UserDto;
import com.gurkhasolutions.userservice.exception.EmailNotFountException;
import com.gurkhasolutions.userservice.exception.UserNotFoundException;
import com.gurkhasolutions.userservice.model.User;
import com.gurkhasolutions.userservice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public ApiResponse createUser(UserDto userDto) {
        ApiResponse apiResponse= new ApiResponse();

        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if(!userByEmail.isPresent()){
            User user= User.builder()
                    .email(userDto.getEmail())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .role(userDto.getRole())
                    .build();
            User savedUser=userRepository.save(user);
            apiResponse.setData(savedUser);
            apiResponse.setMessage("User created successfully");
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return apiResponse;
        }
        apiResponse.setMessage("User with email "+userDto.getEmail()+" already exist.");
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return apiResponse;



    }

    public ApiResponse findUserById(Long userId) {
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> userById = userRepository.findById(userId);
        if(userById.isPresent()){
            apiResponse.setData(userById);
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setMessage("User available");
            return apiResponse;
        }
       throw new UserNotFoundException("User with id "+userId+" not found.");

    }

    public ApiResponse finAllUsers() {
//        userRepository.findAll().stream()
//               .filter(user -> user.getRole().equals("User"))
//               .collect(Collectors.toList());
        ApiResponse apiResponse= new ApiResponse();
        List<User> users=userRepository.findAll();
        apiResponse.setData(users);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return apiResponse;
    }

    public ApiResponse deleteUserById(Long userId) {
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> userById = userRepository.findById(userId);

        if(userById.isPresent()){
            userRepository.deleteById(userId);
            apiResponse.setMessage("User with id "+userId+" deleted successfully");
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return apiResponse;
        }
        apiResponse.setMessage("User with id "+userId+" Not Found.");
        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        return apiResponse;

    }

    public ApiResponse updateUser(Long userId, UserDto userDto) {
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> userById = userRepository.findById(userId);
        if(userById.isPresent()){
            userById.get().setEmail(userDto.getEmail());
            userById.get().setRole(userDto.getRole());
            userById.get().setFirstName(userDto.getFirstName());
            userById.get().setLastName(userDto.getLastName());
            User upadatedUser=userRepository.save(userById.get());
            apiResponse.setData(upadatedUser);
            apiResponse.setMessage("User updated successfully");
            apiResponse.setStatusCode(HttpStatus.OK.value());
            return apiResponse;
        }
        apiResponse.setMessage("User with email "+userDto.getEmail()+" already exist.");
        apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return apiResponse;

    }

    public ApiResponse findUserByEmail(String email) {
        ApiResponse apiResponse= new ApiResponse();
        Optional<User> userWithEmail= userRepository.findByEmail(email);
        if(userWithEmail.isPresent()){
        apiResponse.setData(userWithEmail);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return apiResponse;
        }
        throw new EmailNotFountException("Email with "+email+ " No found");
    }

    public ApiResponse getUsersByRole(String role) {
        ApiResponse apiResponse= new ApiResponse();
        List<User> userWithRole= userRepository.findUserWithRole(role);
        apiResponse.setData(userWithRole);
        apiResponse.setStatusCode(HttpStatus.OK.value());
        return apiResponse;
    }
}
