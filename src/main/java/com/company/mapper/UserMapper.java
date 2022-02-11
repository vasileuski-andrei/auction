package com.company.mapper;

import com.company.dto.UserDto;
import com.company.entity.UserEntity;

public class UserMapper implements Mapper <UserEntity, UserDto> {

    private static final UserMapper INSTANCE = new UserMapper();

    private UserMapper() {
    }

    @Override
    public UserDto mapFrom(UserEntity object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .birthday(object.getBirthDate())
                .email(object.getEmail())
                .build();

    }



    public static UserMapper getInstance() {
        return INSTANCE;
    }




}
