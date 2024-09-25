package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.model.TaskResponse;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    TaskResponse getTaskById(UUID id);
    

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse updateTask(TaskRequest taskRequest, UUID id);

    void deleteTask(UUID id);

    List<TaskResponse> getAllTasks(Integer pageNo, Integer pageSize, String sortBy, Sort.Direction orderBy);
}
