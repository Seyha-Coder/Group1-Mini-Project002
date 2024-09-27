package org.example.taskservice.service;

import org.example.taskservice.feignClient.GroupFeignClient;
import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.example.taskservice.model.response.GroupDto;
import org.example.taskservice.model.response.TaskDto;
import org.example.taskservice.model.response.UserDto;
import org.example.taskservice.repository.TaskRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImp implements TaskService{
    private final TaskRepository taskRepository;
    private final GroupFeignClient groupFeignClient;
    public TaskServiceImp(TaskRepository taskRepository, GroupFeignClient groupFeignClient) {
        this.taskRepository = taskRepository;
        this.groupFeignClient = groupFeignClient;
    }

    @Override
    public TaskDto createTask(TaskRequest taskRequest) {
        Task task = taskRepository.save(taskRequest.toEntity(taskRequest));
        TaskDto taskDto = new TaskDto(
                task.getTaskId().toString(),
                task.getTaskName(),
                task.getDescription(),
                getUserById(task.getCreatedBy()),
                getUserById(task.getAssignedTo()),
                getGroupId(task.getGroupId())
        );
        return taskDto;
    }
    @Override
    public TaskDto getTaskById(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        TaskDto taskDto = new TaskDto(
                task.getTaskId().toString(),
                task.getTaskName(),
                task.getDescription(),
                getUserById(task.getCreatedBy()),
                getUserById(task.getAssignedTo()),
                getGroupId(task.getGroupId())
        );
        return taskDto;
    }

    @Override
    public TaskDto updateTask(TaskRequest taskRequest, UUID id) {
            Task task = taskRepository.findById(id).orElse(null);
            if (task == null) {
               throw new RuntimeException("Task not found");
            }
        taskRepository.save(taskRequest.toEntity(taskRequest));
        TaskDto taskDto = new TaskDto(
                task.getTaskId().toString(),
                task.getTaskName(),
                task.getDescription(),
                getUserById(task.getCreatedBy()),
                getUserById(task.getAssignedTo()),
                getGroupId(task.getGroupId())
        );
        return taskDto;
    }

    @Override
    public Task deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        taskRepository.delete(task);
        return task;
    }

    @Override
    public List<TaskDto> getAllTasks(Long pageNo, Long pageSize, String sortBy, Sort.Direction sortDirection) {
        return null;
    }


    public GroupDto getGroupId(UUID id) {
        GroupDto groupDto = groupFeignClient.getGroupById(id.toString()).getBody().getPayload();
        return  groupDto;
    }
    public UserDto  getUserById(UUID id) {
        UserDto userDto = groupFeignClient.getUserById(id.toString()).getBody().getPayload();
        return userDto;
    }

}
