package com.weber.aihelperapps.Entity;

import com.weber.aihelperapps.Enum.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class TaskEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String message;
    private LocalDateTime date = LocalDateTime.now();
    private Date time = new Date();

    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status = TaskStatusEnum.IN_PROGRESS;

    @ManyToOne
    private UserEntity user;
}
