package org.example.taskservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Group;
import org.apache.catalina.User;
import org.example.taskservice.model.Task;
import org.example.taskservice.model.TaskRequest;
import org.keycloak.representations.idm.UserRepresentation;

@AllArgsConstructor
@Getter
@Setter

public class TaskDto {
    private String taskId;
    private String taskName;
    private String description;
    private User createdBy;
    private User assignedTo;
    private Group groupId;
//    public static TaskDto totaskDto( TaskRequest taskRequest) {
//        Task task = new Task();
//        task.setTaskName(taskRequest.getTaskName());
//        task.setDescription(taskRequest.getDescription());
//        task.setCreatedBy(taskRequest.getCreatedBy());
//        task.setAssignedTo(taskRequest.getAssignedTo());
//        task.setGroupId(taskRequest.getGroupId());
//        return TaskDto.totaskDto(task);
//    }
}
