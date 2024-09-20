package org.example.keycloakadminclient.controller;


import org.example.common.utils.ApiResponse;
import org.example.keycloakadminclient.model.dto.UserDto;
import org.example.keycloakadminclient.model.request.UserRegisterRequest;
import org.example.keycloakadminclient.service.KeycloakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final KeycloakService keycloakService;

    public AuthController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterRequest request){
        UserDto userDto = keycloakService.addUser(request);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Register successfully.")
                .status(HttpStatus.OK)
                .code(201)
                .payload(userDto)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
