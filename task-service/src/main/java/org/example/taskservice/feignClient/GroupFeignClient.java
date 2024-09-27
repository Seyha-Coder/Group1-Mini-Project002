package org.example.taskservice.feignClient;

import org.example.common.utils.ApiResponse;
import org.example.taskservice.model.response.GroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keycloak-admin-client")
public interface GroupFeignClient {
    @GetMapping("/api/v1/group/{id}")
    ApiResponse<GroupDto> getGroupById(@PathVariable String id);
}
