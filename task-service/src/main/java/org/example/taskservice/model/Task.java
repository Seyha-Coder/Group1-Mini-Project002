package org.example.taskservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "task_db")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID taskId;
    private String taskName;
    private String description;
    private UUID createdBy;
    private  UUID assignedTo;
    private UUID groupId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModified;
}
