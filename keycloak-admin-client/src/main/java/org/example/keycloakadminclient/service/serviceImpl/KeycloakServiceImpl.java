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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private final Keycloak keycloak;
    private final ModelMapper modelMapper;
    private final GetCurrentUser getCurrentUser;

    @Value("${keycloak.realm}")
    private String realm;

    private UserRepresentation credential(UserRegisterRequest request) {
        CredentialRepresentation credential = Credentials.createPasswordCredentials(request.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        Map<String, List<String>> attributes = new HashMap<>();
        String currentTime = LocalDateTime.now().toString();
        attributes.put("createdAt", List.of(currentTime));
        attributes.put("lastModified", List.of(currentTime));
        user.setAttributes(attributes);

        user.setCredentials(Collections.singletonList(credential));
        return user;
    }

    private UserRepresentation updateCredential(UserUpdateRequest request) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        Map<String, List<String>> attributes = new HashMap<>();
        String currentTime = LocalDateTime.now().toString();
        attributes.put("lastModified", List.of(currentTime));
        user.setAttributes(attributes);

        return user;
    }

    private void populateUserDto(UserDto userDto, UserRepresentation userRepresentation) {
        userDto.setId(userRepresentation.getId());
        userDto.setUsername(userRepresentation.getUsername());
        userDto.setEmail(userRepresentation.getEmail());
        userDto.setFirstName(userRepresentation.getFirstName());
        userDto.setLastName(userRepresentation.getLastName());

        if (userRepresentation.getCreatedTimestamp() != null) {
            LocalDateTime createdAt = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(userRepresentation.getCreatedTimestamp()),
                    ZoneId.systemDefault()
            );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
            userDto.setCreatedAt(createdAt.format(formatter));
        } else {
            userDto.setCreatedAt(null);
        }

        Map<String, List<String>> attributes = userRepresentation.getAttributes();
        if (attributes != null && attributes.containsKey("lastModified")) {
            userDto.setLastModified(attributes.get("lastModified").get(0));
        } else {
            userDto.setLastModified(null);
        }
    }

    @Override
    public UserDto addUser(UserRegisterRequest request) {
        UserRepresentation user = credential(request);
        user.setEnabled(true);
        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.create(user);

        List<UserRepresentation> createdUsers = usersResource.search(request.getUsername());
        if (!createdUsers.isEmpty()) {
            UserRepresentation createdUser = createdUsers.get(0);
            UserDto userDto = new UserDto();
            populateUserDto(userDto, createdUser);
            return userDto;
        } else {
            throw new CustomNotfoundException("User creation failed");
        }
    }

    @Override
    public UserDto getUserByName(String username) {
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation userRepresentation = usersResource.search(username, true).get(0);
        UserDto userDto = new UserDto();
        populateUserDto(userDto, userRepresentation);
        return userDto;
    }

    @Override
    public UserDto getCurrentUserData(String name) {
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation userRepresentation = usersResource.get(name).toRepresentation();
        UserDto userDto = new UserDto();
        populateUserDto(userDto, userRepresentation);
        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserUpdateRequest userRequest) {
        UserRepresentation user = updateCredential(userRequest);

        Map<String, List<String>> attributes = user.getAttributes();
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        attributes.put("lastModified", List.of(LocalDateTime.now().toString()));
        user.setAttributes(attributes);

        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.get(userId).update(user);
        UserRepresentation updatedUser = usersResource.get(userId).toRepresentation();

        UserDto userDto = new UserDto();
        populateUserDto(userDto, updatedUser);
        return userDto;
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = keycloak.realm(realm).users();
        usersResource.get(userId).remove();
    }

    @Override
    public void resetPassword(String userId, ResetPasswordRequest request) {
        if (!userId.equals(getCurrentUser.currentUserId())) {
            throw new CustomNotfoundException("You don't have permission");
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

        UserDto userDto = new UserDto();
        populateUserDto(userDto, userRepresentation);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UsersResource usersResource = keycloak.realm(realm).users();
        UserRepresentation userRepresentation = usersResource.searchByEmail(email, true).get(0);
        UserDto userDto = new UserDto();
        populateUserDto(userDto, userRepresentation);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        UsersResource usersResource = keycloak.realm(realm).users();
        List<UserRepresentation> userRepresentations = usersResource.list();

        List<UserDto> userDtos = userRepresentations.stream().map(userRepresentation -> {
            UserDto userDto = new UserDto();
            populateUserDto(userDto, userRepresentation);
            return userDto;
        }).collect(Collectors.toList());

        return userDtos;
    }
}
