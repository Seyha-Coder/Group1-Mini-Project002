package org.example.keycloakadminclient.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupDto {
    private UserDto user;
    private GroupDto group;
}
