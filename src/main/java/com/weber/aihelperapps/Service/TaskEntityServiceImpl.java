package com.weber.aihelperapps.Service;

import com.weber.aihelperapps.Entity.TaskEntity;
import com.weber.aihelperapps.Enum.TaskStatusEnum;
import com.weber.aihelperapps.Exception.TaskNotFoundException;
import com.weber.aihelperapps.Repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskEntityServiceImpl implements TaskEntityService {
    private final TaskRepository taskRepository;

    public void createTask(TaskEntity taskEntity) {
        taskRepository.save(taskEntity);
    }

    public void changeTaskStatus(Long id) throws TaskNotFoundException {
        TaskEntity task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Задание не найдено!"));

        task.setStatus(TaskStatusEnum.IN_PROGRESS);

        taskRepository.save(task);
    }

     public List<TaskEntity> findAllInProgressByUserId(Long userId) {
        return taskRepository.getAllInProgressByUserId(userId);
    }
}
