package com.weber.aihelperapps.Service;

import com.weber.aihelperapps.Entity.GroupEntity;
import com.weber.aihelperapps.Exception.GroupNotFoundException;

public interface GroupEntityService {
    void create(GroupEntity groupEntity);

    GroupEntity findByChatId(String chatId) throws GroupNotFoundException;
}
