package com.dev.service;

import com.dev.dao.UserDao;
import com.dev.dto.CreateUserDto;
import com.dev.dto.UserDto;
import com.dev.entity.Gender;
import com.dev.entity.Role;
import com.dev.entity.User;
import com.dev.integration.IntegrationTestBase;
import com.dev.mapper.CreateUserMapper;
import com.dev.mapper.UserMapper;
import com.dev.validator.CreateUserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceIT extends IntegrationTestBase {

    private UserDao userDao;
    private UserService userService;

    @BeforeEach
    void init() {
        userDao = UserDao.getInstance();
        userService = new UserService(
                CreateUserValidator.getInstance(),
                userDao,
                CreateUserMapper.getInstance(),
                UserMapper.getInstance()
        );
    }

    @Test
    void login() {
        User user = userDao.save(getUser("test@gmail.com"));

        Optional<UserDto> actualResult = userService.login(user.getEmail(), user.getPassword());

        assertThat(actualResult).isPresent();
        assertThat(actualResult.get().getId()).isEqualTo(user.getId());
    }

    @Test
    void create() {
        CreateUserDto createUserDto = getCreateUserDto();

        UserDto actualResult = userService.create(createUserDto);

        assertNotNull(actualResult.getId());
    }

    private CreateUserDto getCreateUserDto() {
        return CreateUserDto.builder()
                .name("Ivan")
                .email("test@gmail.com")
                .password("123")
                .birthday("2000-01-01")
                .role(Role.USER.name())
                .gender(Gender.MALE.name())
                .build();
    }

    private User getUser(String email) {
        return User.builder()
                .name("Ivan")
                .gender(Gender.MALE)
                .role(Role.USER)
                .email(email)
                .password("123")
                .birthday(LocalDate.of(2000, 1, 1))
                .build();
    }
}