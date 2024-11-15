package com.weber.aihelperapps.Request;

import lombok.Getter;

@Getter
public class CreateTaskRequest {
    private String message;
    private Long tgUserId;
    private String tgChatId;
    private String tgChatName;
}
