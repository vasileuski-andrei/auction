package com.company.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class CreateUserDto {
    Integer id;
    String name;
    String birthday;
    String email;
    String password;
    String passwordConfirmation;
}
