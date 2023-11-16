package com.dev.service;

import com.dev.dao.UserDao;
import com.dev.dto.CreateUserDto;
import com.dev.dto.UserDto;
import com.dev.exception.ValidationException;
import com.dev.mapper.CreateUserMapper;
import com.dev.mapper.UserMapper;
import com.dev.validator.CreateUserValidator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final CreateUserValidator createUserValidator;
    private final UserDao userDao;
    private final CreateUserMapper createUserMapper;
    private final UserMapper userMapper;

    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::map);
    }

    @SneakyThrows
    public UserDto create(CreateUserDto userDto) {
        var validationResult = createUserValidator.validate(userDto);
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.map(userDto);
        userDao.save(userEntity);

        return userMapper.map(userEntity);
    }
}