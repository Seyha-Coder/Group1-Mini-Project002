package org.example.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.taskservice.model.response.TaskDto;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskRequest {
    private String taskName;
    private String description;
    private UUID createdBy;
    private  UUID assignedTo;
    private UUID groupId;

    public Task toDto(TaskRequest taskRequest) {
       Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setDescription(taskRequest.getDescription());
        task.setCreatedBy(taskRequest.getCreatedBy());
        task.setAssignedTo(taskRequest.getAssignedTo());
        task.setGroupId(taskRequest.getGroupId());
        return task;
    }
}
