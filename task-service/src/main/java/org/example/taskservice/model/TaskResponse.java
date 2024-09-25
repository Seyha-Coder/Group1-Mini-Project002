package org.example.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskResponse {
    private UUID taskId;
    private String taskName;
    private String description;
    private User createdBy;
    private  User assignedTo;
    private Group groupId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;


}
