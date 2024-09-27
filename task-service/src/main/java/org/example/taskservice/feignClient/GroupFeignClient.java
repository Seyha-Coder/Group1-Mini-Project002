package org.example.taskservice.feignClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.common.utils.ApiResponse;
import org.example.taskservice.model.response.GroupDto;
import org.example.taskservice.model.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@FeignClient(name = "keycloak-admin-client")
public interface GroupFeignClient {
    @GetMapping("/api/v1/groups/{id}")
//    @CircuitBreaker(name = "keyscloak-admin-client", fallbackMethod = "groupFallback")
   ResponseEntity<ApiResponse<GroupDto>> getGroupById(@PathVariable String id);
//    default  ResponseEntity<?> groupFallback(@PathVariable String id) {
//        ApiResponse<?> response = ApiResponse.builder()
//                .message("Get group successfully")
//                .payload(new GroupDto("Fallback","Fallback"))
//                .status(HttpStatus.OK)
//                .code(HttpStatus.OK.value())
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }


    @GetMapping("/api/v1/user/{id}")
    ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable String id);
//    @CircuitBreaker(name = "keyscloak-admin-client", fallbackMethod = "userFallback")
//    ResponseEntity<ApiResponse<UserDto>> userFallback(@PathVariable String id);
//    default  ResponseEntity<ApiResponse<UserDto>> GroupFallback(@PathVariable String id) {
//        ApiResponse<UserDto> response = ApiResponse.<UserDto>builder()
//                .message("Get group successfully")
//                .payload(new UserDto("Fallback","Fallback","Fallback","Fallback","Fallback"))
//                .status(HttpStatus.OK)
//                .code(HttpStatus.OK.value())
//                .timestamp(LocalDateTime.now())
//                .build();
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
}

