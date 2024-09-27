package org.example.taskservice.service;

import org.example.taskservice.feignClient.GroupFeignClient;
import org.example.taskservice.feignClient.UserFeignClient;
import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.model.response.GroupDto;
import org.example.taskservice.model.response.TaskDto;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepository;
    private final UserFeignClient userFeignClient;
    private final GroupFeignClient groupFeignClient;
    public TaskServiceImp(TaskRepository taskRepository, UserFeignClient userFeignClient, GroupFeignClient groupFeignClient) {
        this.taskRepository = taskRepository;
        this.userFeignClient = userFeignClient;
        this.groupFeignClient = groupFeignClient;
    }

    @Override
    public Task createTask(TaskRequest taskRequest) {
        Task task = taskRepository.save(taskRequest.toDto(taskRequest));
//        TaskDto taskDto = new TaskDto(
//                task.getTaskName(),
//                task.getDescription(),
//
//        );
        return task;
    }
    @Override
    public Task getTaskById(UUID id) {

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
