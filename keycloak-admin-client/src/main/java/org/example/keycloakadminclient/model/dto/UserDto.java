package org.example.keycloakadminclient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String createdAt;
    private String lastModified;

    public static UserDto toUserDto(UserRepresentation userRepresentation) {
        UserDto userDto = new UserDto();
        userDto.setId(userRepresentation.getId());
        userDto.setUsername(userRepresentation.getUsername());
        userDto.setEmail(userRepresentation.getEmail());
        userDto.setFirstName(userRepresentation.getFirstName());
        userDto.setLastName(userRepresentation.getLastName());

        if (userRepresentation.getAttributes() != null) {
            userDto.setCreatedAt(userRepresentation.getAttributes().getOrDefault("createdAt", List.of("")).get(0));
            userDto.setLastModified(userRepresentation.getAttributes().getOrDefault("lastModified", List.of("")).get(0));
        }

        return userDto;
    }

}
