package org.example.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
