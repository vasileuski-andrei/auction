package com.company.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class UserDto {

    private Integer id;
    private String name;
    private LocalDate birthday;
    private String email;
}
