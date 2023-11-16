package com.dev.dto;

import com.dev.entity.Gender;
import com.dev.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    Role role;
    Gender gender;
}