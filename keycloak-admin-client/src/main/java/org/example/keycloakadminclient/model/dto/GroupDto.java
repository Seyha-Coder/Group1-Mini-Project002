package org.example.keycloakadminclient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.keycloak.representations.idm.GroupRepresentation;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private String groupId;
    private String groupName;

    public static GroupDto toGroupDto(GroupRepresentation groupRepresentation) {
        return new GroupDto(groupRepresentation.getId(), groupRepresentation.getName());
    }

}
