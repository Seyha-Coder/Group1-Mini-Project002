package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.model.response.TaskDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    TaskDto getTaskById(UUID id);

    TaskDto createTask(TaskRequest taskRequest);

    TaskDto updateTask(TaskRequest taskRequest, UUID id);

    Task deleteTask(UUID id);

    List<TaskDto> getAllTasks(Long pageNo, Long pageSize, String sortBy, Sort.Direction sortDirection);
}
