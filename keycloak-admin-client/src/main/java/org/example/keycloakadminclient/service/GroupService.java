package org.example.keycloakadminclient.service;

import org.example.keycloakadminclient.model.dto.GroupDto;
import org.example.keycloakadminclient.model.dto.UserGroupDto;
import org.example.keycloakadminclient.model.dto.UsersInGroupDto;
import org.example.keycloakadminclient.model.request.GroupRequest;

import java.util.List;
import java.util.UUID;

public interface GroupService {
    List<GroupDto> getAllGroup();

    GroupDto getGroupById(String groupId);

    GroupDto addGroup(GroupRequest groupRequest);

    GroupDto updateGroup(UUID groupId, GroupRequest groupRequest);

    GroupDto deleteGroup(UUID groupId);

    UserGroupDto addUserToGroup(UUID groupId, UUID userId);

    UsersInGroupDto getUsersInGroup(UUID groupId);
}
