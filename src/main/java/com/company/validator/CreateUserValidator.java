package com.company.validator;

import com.company.dto.CreateUserDto;
import com.company.util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        var validationResult = new ValidationResult();
        if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(Error.of("invalid-birthday", "gender is invalid"));
        }

        //if (object.getPassword().equals(object))
//        if (Gender.valueOf(object.getGender()) == null) {
//            validationResult.add(Error.of("invalid", "gender is invalid"));
//        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
