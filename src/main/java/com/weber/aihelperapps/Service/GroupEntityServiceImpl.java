package com.weber.aihelperapps.Service;

import com.weber.aihelperapps.Entity.GroupEntity;
import com.weber.aihelperapps.Exception.GroupNotFoundException;
import com.weber.aihelperapps.Repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupEntityServiceImpl implements GroupEntityService {
    private final GroupRepository groupRepository;

    @Override
    public void create(GroupEntity groupEntity) {
        groupRepository.save(groupEntity);
    }

    @Override
    public GroupEntity findByChatId(String chatId) throws GroupNotFoundException {
        return groupRepository.findByChatId(chatId).orElseThrow(() -> new GroupNotFoundException(chatId));
    }
}
