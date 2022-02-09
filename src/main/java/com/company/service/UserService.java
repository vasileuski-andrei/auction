package com.company.service;

import com.company.dao.UserDao;
import com.company.dto.CreateUserDto;
import com.company.dto.UserDto;
import com.company.exception.ValidationException;
import com.company.mapper.CreateUserMapper;
import com.company.mapper.UserMapper;
import com.company.validator.CreateUserValidator;
import lombok.NoArgsConstructor;
import java.util.Optional;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao personDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();

    public Optional<UserDto> login(String email, String password) {
        return personDao.findByEmailAndPassword(email, password).map(userMapper::mapFrom);

    }

    public Integer create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var userEntity = createUserMapper.mapFrom(userDto);
        personDao.save(userEntity);

        return userEntity.getId();

    }

    public static UserService getInstance() {
        return INSTANCE;
    }


}
