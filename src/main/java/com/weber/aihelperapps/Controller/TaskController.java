package com.weber.aihelperapps.Controller;

import com.weber.aihelperapps.Entity.GroupEntity;
import com.weber.aihelperapps.Entity.TaskEntity;
import com.weber.aihelperapps.Entity.UserEntity;
import com.weber.aihelperapps.Exception.GroupNotFoundException;
import com.weber.aihelperapps.Exception.UserNotFoundException;
import com.weber.aihelperapps.Request.CreateTaskRequest;
import com.weber.aihelperapps.Service.GroupEntityServiceImpl;
import com.weber.aihelperapps.Service.TaskEntityServiceImpl;
import com.weber.aihelperapps.Service.UserEntityServiceImpl;
import com.weber.aihelperapps.Utils.OpenAIChatHelper;
import com.weber.aihelperapps.Utils.TaskNameGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskEntityServiceImpl taskEntityServiceImpl;
    private final GroupEntityServiceImpl groupEntityServiceImpl;
    private final UserEntityServiceImpl userEntityServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setMessage(createTaskRequest.getMessage());


        taskEntity.setName(TaskNameGenerator.generateTaskName(createTaskRequest.getMessage()));

        try {
            // Проверка существующей группы по chatId
            GroupEntity group = groupEntityServiceImpl.findByChatId(createTaskRequest.getTgChatId());
            taskEntity.setUser(userEntityServiceImpl.findUserByTgId(createTaskRequest.getTgUserId()));
        } catch (GroupNotFoundException | UserNotFoundException e) {
            // Если группа не найдена, создаем ее
            GroupEntity group = new GroupEntity();
            group.setChatId(createTaskRequest.getTgChatId());
            group.setChatName(createTaskRequest.getTgChatName());

            groupEntityServiceImpl.create(group);
        }

        try {
            UserEntity user = userEntityServiceImpl.findUserByTgId(createTaskRequest.getTgUserId());
            taskEntity.setUser(user);
        } catch (UserNotFoundException e) {
            UserEntity user = new UserEntity();

            user = new UserEntity();
            user.setTgUserId(createTaskRequest.getTgUserId());
            user.setGroup(groupEntityServiceImpl.findByChatId(createTaskRequest.getTgChatId()));

            userEntityServiceImpl.createUser(user);

            taskEntity.setUser(user);
        }

        // Создание задачи
        taskEntityServiceImpl.createTask(taskEntity);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/get-all-by-user-id-in-progress")
    public ResponseEntity<?> getAllByUserIdInProgress(@RequestParam Long userId) {
        return new ResponseEntity<>(taskEntityServiceImpl.findAllInProgressByUserId(userId), HttpStatus.OK);
    }
}
