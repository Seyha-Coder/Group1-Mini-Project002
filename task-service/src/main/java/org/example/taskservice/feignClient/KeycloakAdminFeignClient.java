package org.example.taskservice.feignClient;

import org.example.common.utils.ApiResponse;
import org.example.taskservice.model.Group;
import org.example.taskservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keycloak-admin-client")
public interface KeycloakAdminFeignClient {
    @GetMapping("/api/v1/groups/{groupId}")
    ResponseEntity<ApiResponse<Group>> getGroupById(@PathVariable String groupId);

    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<ApiResponse<User>> getUserById (@PathVariable String id);

}
