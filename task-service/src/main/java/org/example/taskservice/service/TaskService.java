package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    Task getTaskById(UUID id);

    Task createTask(TaskRequest taskRequest);

    Task updateTask(TaskRequest taskRequest, UUID id);

    Task deleteTask(UUID id);

    List<Task> getAllTasks(Long pageNo, Long pageSize, String sortBy, Sort.Direction sortDirection);
}
