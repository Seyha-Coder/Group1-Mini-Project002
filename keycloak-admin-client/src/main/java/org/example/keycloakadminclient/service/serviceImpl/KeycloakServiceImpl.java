package org.example.keycloakadminclient.service.serviceImpl;


import lombok.RequiredArgsConstructor;
import org.example.common.exception.CustomNotfoundException;
import org.example.keycloakadminclient.config.GetCurrentUser;
import org.example.keycloakadminclient.model.dto.UserDto;
import org.example.keycloakadminclient.model.request.ResetPasswordRequest;
import org.example.keycloakadminclient.model.request.UserRegisterRequest;
import org.example.keycloakadminclient.model.request.UserUpdateRequest;
import org.example.keycloakadminclient.security.Credentials;
import org.example.keycloakadminclient.service.KeycloakService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;
    private final ModelMapper modelMapper;
    private final GetCurrentUser getCurrentUser;
    @Value("${keycloak.realm}")
    private String realm;

    private UserRepresentation credential(UserRegisterRequest request){
        CredentialRepresentation credential = Credentials.createPasswordCredentials(request.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCredentials(Collections.singletonList(credential));
        return user;
    }
    private UserRepresentation updateCredential(UserUpdateRequest request){

        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        return user;
    }
    @Override
    public UserDto addUser(UserRegisterRequest request){
        UserRepresentation user = credential(request);
        user.setEnabled(true);
        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.create(user);
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    public UserDto getUserByName(String username){
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation userRepresentation = usersResource.search(username,true).get(0);
        return modelMapper.map(userRepresentation, UserDto.class);
    }
    @Override
    public UserDto getCurrentUserData(String name) {
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation userRepresentation = usersResource.get(name).toRepresentation();
        return modelMapper.map(userRepresentation, UserDto.class);
    }
    @Override
    public UserDto updateUser(String userId, UserUpdateRequest userRequest) {
        UserRepresentation user = updateCredential(userRequest);
        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.get(userId).update(user);
        return modelMapper.map(user, UserDto.class);
    }
    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.get(userId).remove();
    }
    @Override
    public void resetPassword(String userId, ResetPasswordRequest request) {
        if(!userId.equals(getCurrentUser.currentUserId())){
            throw new CustomNotfoundException("you don't have permission");
        }
        CredentialRepresentation credentialRepresentation = Credentials.createPasswordCredentials(request.getPassword());
        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.get(userId).resetPassword(credentialRepresentation);
    }
    @Override
    public UserDto getUserById(String userId) {
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation userRepresentation = usersResource.get(userId).toRepresentation();
        if (userRepresentation == null) {
            throw new CustomNotfoundException("User not found with ID: " + userId);
        }
        return modelMapper.map(userRepresentation, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UsersResource usersResource = keycloak.realm(realm).users();
        List<UserRepresentation> userRepresentations = usersResource.search(null, null, null, email, null, null);
        if (userRepresentations.isEmpty()) {
            throw new CustomNotfoundException("User not found with email: " + email);
        }
        return modelMapper.map(userRepresentations.get(0), UserDto.class);
    }



    @Override
    public List<UserDto> getAllUsers() {
        UsersResource usersResource = keycloak.realm(realm).users();
        List<UserRepresentation> userRepresentations = usersResource.list();

        List<UserDto> userDtos = userRepresentations.stream().map(userRepresentation -> {
            UserDto userDto = new UserDto();
            userDto.setUsername(userRepresentation.getUsername());
            userDto.setFirstName(userRepresentation.getFirstName());
            userDto.setLastName(userRepresentation.getLastName());
            userDto.setEmail(userRepresentation.getEmail());
            return userDto;
        }).collect(Collectors.toList());

        return userDtos;
    }


}
