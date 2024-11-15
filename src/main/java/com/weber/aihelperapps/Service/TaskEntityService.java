package com.weber.aihelperapps.Service;

import com.weber.aihelperapps.Entity.TaskEntity;
import com.weber.aihelperapps.Enum.TaskStatusEnum;
import com.weber.aihelperapps.Exception.TaskNotFoundException;

import java.util.List;

public interface TaskEntityService {

    void createTask(TaskEntity taskEntity);


    void changeTaskStatus(Long id) throws TaskNotFoundException;

    List<TaskEntity> findAllInProgressByUserId(Long userId);
}
