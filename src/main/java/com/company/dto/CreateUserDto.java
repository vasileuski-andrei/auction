package com.company.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
@Builder
public class CreateUserDto {
    private Integer id;
    private String name;
    private String birthday;
    private String email;
    private String password;
    private String passwordConfirmation;
}
