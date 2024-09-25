package org.example.keycloakadminclient.service.serviceImpl;

import jakarta.ws.rs.core.Response;
import org.example.keycloakadminclient.model.dto.GroupDto;
import org.example.keycloakadminclient.model.dto.UserDto;
import org.example.keycloakadminclient.model.dto.UserGroupDto;
import org.example.keycloakadminclient.model.dto.UsersInGroupDto;
import org.example.keycloakadminclient.model.request.GroupRequest;
import org.example.keycloakadminclient.service.GroupService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    public String realm;

    public GroupServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public List<GroupDto> getAllGroup() {
        RealmResource realmResource = keycloak.realm(realm);
        GroupsResource groupsResource = realmResource.groups();
        List<GroupRepresentation> groups = groupsResource.groups();
        return groups.stream()
                .map(GroupDto::toGroupDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto getGroupById(String groupId) {
        RealmResource realmResource = keycloak.realm(realm);
        GroupResource groupsResource = realmResource.groups().group(groupId);
        GroupRepresentation groupRepresentation = groupsResource.toRepresentation();
        return GroupDto.toGroupDto(groupRepresentation);
    }

    @Override
    public GroupDto addGroup(GroupRequest groupRequest) {
        RealmResource realmResource = keycloak.realm(realm);
        GroupRepresentation groupRepresentation = new GroupRepresentation();
        groupRepresentation.setName(groupRequest.getGroupName());
        realmResource.groups().add(groupRepresentation);
        List<GroupRepresentation> listGroup = realmResource.groups().query(groupRequest.getGroupName());
        for (GroupRepresentation group : listGroup) {
            if (group.getName().equals(groupRequest.getGroupName())) {
                groupRepresentation.setId(group.getId());
            }
        }
        return GroupDto.toGroupDto(groupRepresentation);
    }

    @Override
    public GroupDto updateGroup(UUID groupId, GroupRequest groupRequest) {
        RealmResource realmResource = keycloak.realm(realm);
        GroupResource groupsResource = realmResource.groups().group(groupId.toString());
        GroupRepresentation groupRepresentation = groupsResource.toRepresentation();
        groupRepresentation.setName(groupRequest.getGroupName());
        groupsResource.update(groupRepresentation);
        return GroupDto.toGroupDto(groupRepresentation);
    }

    @Override
    public GroupDto deleteGroup(UUID groupId) {
        RealmResource realmResource = keycloak.realm(realm);
        GroupResource groupsResource = realmResource.groups().group(groupId.toString());
        groupsResource.remove();
        return null;
    }

    @Override
    public UserGroupDto addUserToGroup(UUID groupId, UUID userId) {
        RealmResource realmResource = keycloak.realm(realm);
        GroupResource groupsResource = realmResource.groups().group(groupId.toString());
        GroupRepresentation groupRepresentation = groupsResource.toRepresentation();
        UserResource userResource = realmResource.users().get(userId.toString());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        if (groupRepresentation != null && userRepresentation != null) {
            userResource.joinGroup(groupId.toString());
        }
        UserGroupDto userGroupDto = new UserGroupDto();
        assert userRepresentation != null;
        userGroupDto.setUser(UserDto.toUserDto(userRepresentation));
        assert groupRepresentation != null;
        userGroupDto.setGroup(GroupDto.toGroupDto(groupRepresentation));
        return userGroupDto;
    }

    @Override
    public UsersInGroupDto getUsersInGroup(UUID groupId) {
        RealmResource realmResource = keycloak.realm(realm);
        GroupResource groupsResource = realmResource.groups().group(groupId.toString());
        GroupRepresentation groupRepresentation = groupsResource.toRepresentation();
        List<UserRepresentation> members = groupsResource.members().stream().toList();
        return UsersInGroupDto.usersInGroupDto(groupRepresentation, members);
    }
}
