package org.example.taskservice.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.common.utils.ApiResponse;
import org.example.taskservice.feignClient.KeycloakAdminFeignClient;
import org.example.taskservice.model.*;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private final KeycloakAdminFeignClient keycloakAdminFeignClient;
    private final TaskRepository taskRepository;

    public TaskServiceImp(KeycloakAdminFeignClient keycloakAdminFeignClient, TaskRepository taskRepository) {
        this.keycloakAdminFeignClient = keycloakAdminFeignClient;
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        return task;
    }


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskRepository.save(taskRequest.toEntity());
        User createdBy = keycloakAdminFeignClient.getUserById(taskRequest.getCreatedBy().toString()).getBody().getPayload();
        User assignedTo = keycloakAdminFeignClient.getUserById(taskRequest.getAssignedTo().toString()).getBody().getPayload();
        Group group = keycloakAdminFeignClient.getGroupById(taskRequest.getGroupId().toString()).getBody().getPayload();
        return task.toResponse(createdBy,assignedTo,group);
    }

    @Override
    public TaskResponse updateTask(TaskRequest taskRequest, UUID id) {
        Task updateTask = taskRepository.save(taskRequest.toEntity(id));
        User createdBy = keycloakAdminFeignClient.getUserById(taskRequest.getCreatedBy().toString()).getBody().getPayload();
        User assignedTo = keycloakAdminFeignClient.getUserById(taskRequest.getAssignedTo().toString()).getBody().getPayload();
        Group group = keycloakAdminFeignClient.getGroupById(taskRequest.getGroupId().toString()).getBody().getPayload();
        return updateTask.toResponse(createdBy,assignedTo,group);
    }

    @Override
    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orderBy,sortBy));
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.getContent();
    }
}
