package com.dev.mapper;

import com.dev.dto.CreateUserDto;
import com.dev.entity.Gender;
import com.dev.entity.Role;
import com.dev.entity.User;
import com.dev.util.LocalDateFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateUserMapper implements Mapper<CreateUserDto, User> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User map(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .email(object.getEmail())
                .password(object.getPassword())
                .gender(Gender.findOpt(object.getGender()).orElse(null))
                .role(Role.findOpt(object.getRole()).orElse(null))
                .build();
    }
}