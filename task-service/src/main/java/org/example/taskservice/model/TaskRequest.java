package org.example.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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

    public Task toEntity(){
        return new Task(null,this.taskName,this.description,this.createdBy,this.assignedTo,this.groupId, LocalDateTime.now(),LocalDateTime.now());
    }
    public Task toEntity(UUID id){
        return new Task(id,this.taskName,this.description,this.createdBy,this.assignedTo,this.groupId, LocalDateTime.now(),LocalDateTime.now());
    }
}
