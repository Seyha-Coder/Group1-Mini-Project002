package org.example.keycloakadminclient.controller;


import org.example.common.utils.ApiResponse;
import org.example.keycloakadminclient.model.dto.GroupDto;
import org.example.keycloakadminclient.model.request.GroupRequest;
import org.example.keycloakadminclient.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<?> getAllGroup() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get all group successfully")
                .payload(groupService.getAllGroup())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/{groupId}")
    public ResponseEntity<?> getGroupById(@PathVariable String groupId) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get group successfully")
                .payload(groupService.getGroupById(groupId))
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping
    public ResponseEntity<?> addGroup(@RequestBody GroupRequest groupRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Add group successfully")
                .payload(groupService.addGroup(groupRequest))
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(@PathVariable UUID groupId, @RequestBody GroupRequest groupRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Update group successfully")
                .payload(groupService.updateGroup(groupId,groupRequest))
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable UUID groupId) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete group successfully")
                .payload(groupService.deleteGroup(groupId))
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/{groupId}/users/{userId}")
    public ResponseEntity<?> addUserToGroup(@PathVariable UUID groupId, @PathVariable UUID userId) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Add user to group successfully")
                .payload(groupService.addUserToGroup(groupId,userId))
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{groupId}/users")
    public ResponseEntity<?> getUsersInGroup(@PathVariable UUID groupId) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get users in group successfully")
                .payload(groupService.getUsersInGroup(groupId))
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
