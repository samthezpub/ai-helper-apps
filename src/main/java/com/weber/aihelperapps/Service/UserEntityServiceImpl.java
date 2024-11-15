package com.weber.aihelperapps.Service;

import com.weber.aihelperapps.Entity.UserEntity;
import com.weber.aihelperapps.Exception.UserNotFoundException;
import com.weber.aihelperapps.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserEntityServiceImpl implements UserEntityService {
    private final UserRepository userRepository;

    @Override
    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public UserEntity findUserByTgId(Long tgId) throws UserNotFoundException {
        return userRepository.findByTgUserId(tgId).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }
}
