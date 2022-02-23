package com.company.mapper;

import com.company.dto.CreateUserDto;
import com.company.entity.UserEntity;
import com.company.util.LocalDateTimeFormatter;

public class CreateUserMapper implements Mapper<CreateUserDto, UserEntity> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    private CreateUserMapper() {
    }

    @Override
    public UserEntity mapFrom(CreateUserDto object) {
        return UserEntity.builder()
                .name(object.getName())
                .birthDate(LocalDateTimeFormatter.convertStringToLocalDate(object.getBirthday(), "yyyy-MM-dd"))
                .email(object.getEmail())
                .password(object.getPassword())
                .role(object.getRole())
                .build();
    }

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

}
