package org.example.keycloakadminclient.service;


import org.example.keycloakadminclient.model.dto.UserDto;
import org.example.keycloakadminclient.model.request.ResetPasswordRequest;
import org.example.keycloakadminclient.model.request.UserRegisterRequest;
import org.example.keycloakadminclient.model.request.UserUpdateRequest;

public interface KeycloakService {
    UserDto addUser(UserRegisterRequest request);
    UserDto getUserByName(String username);
    UserDto getCurrentUserData(String name);
    UserDto updateUser(String userId, UserUpdateRequest userRequest);
    void deleteUser(String userId);
    void resetPassword(String userId, ResetPasswordRequest request);
    UserDto getUserById(String userId);
}
