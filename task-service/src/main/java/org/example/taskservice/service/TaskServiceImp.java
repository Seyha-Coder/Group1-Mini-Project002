package org.example.taskservice.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.model.TaskResponse;
import org.example.taskservice.model.User;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Task task = taskRepository.findById(id).orElse(null);
        return task;
    }


    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = taskRepository.save(taskRequest.toEntity());
        return task.toResponse(null,null,null);
    }

    @Override
    public TaskResponse updateTask(TaskRequest taskRequest, UUID id) {
        Task updateTask = taskRepository.save(taskRequest.toEntity(id));
        return updateTask.toResponse(null,null,null);
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
