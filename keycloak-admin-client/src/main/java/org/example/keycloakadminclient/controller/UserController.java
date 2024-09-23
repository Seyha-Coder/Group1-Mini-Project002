package org.example.keycloakadminclient.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.common.utils.ApiResponse;
import org.example.keycloakadminclient.model.dto.UserDto;
import org.example.keycloakadminclient.model.request.ResetPasswordRequest;
import org.example.keycloakadminclient.model.request.UserUpdateRequest;
import org.example.keycloakadminclient.service.KeycloakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@SecurityRequirement(name = "oauth")
public class UserController {
    private final KeycloakService keycloakService;

    public UserController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }
    @GetMapping("{name}")
    public ResponseEntity<ApiResponse> getUserByName(@PathVariable String name) {
        UserDto userDto = keycloakService.getUserByName(name);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(userDto)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ApiResponse> getUserByName(Principal principal) {
        UserDto userDto = keycloakService.getCurrentUserData(principal.getName());
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(userDto)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String id, @RequestBody UserUpdateRequest request) {
        UserDto userDto = keycloakService.updateUser(id,request);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Updated Success")
                .status(HttpStatus.OK)
                .code(200)
                .payload(userDto)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @PutMapping("/reset-password/{id}")
    public ResponseEntity<ApiResponse> resetPassword(@PathVariable String id, @RequestBody ResetPasswordRequest request) {
        keycloakService.resetPassword(id, request);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Reset Success")
                .status(HttpStatus.OK)
                .code(200)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id) {
        keycloakService.deleteUser(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Deleted Success")
                .status(HttpStatus.OK)
                .code(200)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable String email) {
        UserDto userDto = keycloakService.getUserByEmail(email);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("User with email "+ email +" get successfully.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(userDto)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/allUsers")
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<UserDto> userDtos = keycloakService.getAllUsers();
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Users get successfully.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(userDtos)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable String id) {
        UserDto userDto = keycloakService.getUserById(id);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("User get successfully.")
                .status(HttpStatus.OK)
                .code(200)
                .payload(userDto)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
