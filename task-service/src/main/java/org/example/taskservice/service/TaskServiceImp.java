package org.example.taskservice.service;

import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepository;

    public TaskServiceImp(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getTaskById(UUID id) {
        return null;
    }


    @Override
    public Task createTask(TaskRequest taskRequest) {
        return null;
    }

    @Override
    public Task updateTask(TaskRequest taskRequest, UUID id) {
        return null;
    }

    @Override
    public Task deleteTask(UUID id) {
        return null;
    }

    @Override
    public List<Task> getAllTasks(Long pageNo, Long pageSize, String sortBy, Sort.Direction sortDirection) {
        return null;
    }
}
