package org.example.taskservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Group;
import org.apache.catalina.User;

@AllArgsConstructor
@Getter
@Setter

public class TaskResponse {
    private String taskId;
    private String taskName;
    private String description;
    private User createdBy;
    private User assignedTo;
    private Group groupId;
}
