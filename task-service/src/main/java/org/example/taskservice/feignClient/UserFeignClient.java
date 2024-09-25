package org.example.taskservice.feignClient;

import org.example.common.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClient {
    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<ApiResponse> getUserById(@PathVariable String id);

}
