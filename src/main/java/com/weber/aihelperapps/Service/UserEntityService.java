package com.weber.aihelperapps.Service;

import com.weber.aihelperapps.Entity.UserEntity;
import com.weber.aihelperapps.Exception.UserNotFoundException;

public interface UserEntityService {
    void createUser(UserEntity user);
    UserEntity findUserByTgId(Long tgId) throws UserNotFoundException;
}
