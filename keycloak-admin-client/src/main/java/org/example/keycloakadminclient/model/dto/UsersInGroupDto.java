package org.example.keycloakadminclient.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersInGroupDto {
    private GroupDto group;
    private List<UserDto> listUsers;

    public static UsersInGroupDto usersInGroupDto(GroupRepresentation groupRepresentation, List<UserRepresentation> list) {
        UsersInGroupDto usersInGroupDto = new UsersInGroupDto();
        usersInGroupDto.setGroup(GroupDto.toGroupDto(groupRepresentation));
        List<UserDto> userDto = new ArrayList<>();
        for (UserRepresentation userRepresentation : list) {
            userDto.add(UserDto.toUserDto(userRepresentation));
        }
        usersInGroupDto.setListUsers(userDto);
        return usersInGroupDto;
    }
}
