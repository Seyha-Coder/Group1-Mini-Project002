package org.example.taskservice.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.common.utils.ApiResponse;
import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.model.response.TaskDto;
import org.example.taskservice.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/task")
@SecurityRequirement(name = "oauth")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<?> getAllTasks(@RequestParam (defaultValue = "0") Long pageNo,
                                         @RequestParam (defaultValue = "10") Long pageSize,
                                         @RequestParam (defaultValue = "taskName") String sortBy,
                                         @RequestParam (defaultValue = "ASC") Sort.Direction sortDirection){
        List<TaskDto> tasks = taskService.getAllTasks(pageNo,pageSize,sortBy,sortDirection);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get all task is successfully!")
                .code(200)
                .payload(tasks)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getAllById(@PathVariable UUID id){
        TaskDto task = taskService.getTaskById(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Get by id task is successfully!")
                .payload(task)
                .code(200)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskRequest taskRequest){
        TaskDto taskDto = taskService.createTask(taskRequest);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .message("Created task successfully!")
                .payload(taskDto)
                .code(201)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateTask(@RequestBody TaskRequest taskRequest , @PathVariable UUID id){
        TaskDto task = taskService.updateTask(taskRequest,id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Updated successfully")
                .payload(task)
                .code(200)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable UUID id){
        Task task = taskService.deleteTask(id);
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("Deleted by id successfully!")
                .payload(task)
                .code(200)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
