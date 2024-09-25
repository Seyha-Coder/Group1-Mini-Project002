package org.example.taskservice.service;

import org.example.taskservice.feignClient.KeycloakAdminFeignClient;
import org.example.taskservice.model.*;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public TaskResponse getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        User createdBy = keycloakAdminFeignClient.getUserById(task.getCreatedBy().toString()).getBody().getPayload();
        User assignedTo = keycloakAdminFeignClient.getUserById(task.getAssignedTo().toString()).getBody().getPayload();
        Group group = keycloakAdminFeignClient.getGroupById(task.getGroupId().toString()).getBody().getPayload();
        return task.toResponse(createdBy,assignedTo,group);
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
    public List<TaskResponse> getAllTasks(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy) {
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(orderBy,sortBy));
        Page<Task> tasks = taskRepository.findAll(pageable);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task : tasks){
            User createdBy = keycloakAdminFeignClient.getUserById(task.getCreatedBy().toString()).getBody().getPayload();
            User assignedTo = keycloakAdminFeignClient.getUserById(task.getAssignedTo().toString()).getBody().getPayload();
            Group group = keycloakAdminFeignClient.getGroupById(task.getGroupId().toString()).getBody().getPayload();
            taskResponses.add(task.toResponse(createdBy,assignedTo,group));
        }
        return taskResponses;
    }
}
