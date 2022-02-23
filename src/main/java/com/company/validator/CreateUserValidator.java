package com.company.validator;

import com.company.dto.CreateUserDto;
import com.company.util.LocalDateTimeFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    private CreateUserDto createUserDto;
    private ValidationResult validationResult;

    @Override
    public ValidationResult validateData(CreateUserDto object) {
        createUserDto = object;
        validationResult = new ValidationResult();

        checkUsername();
        checkUserDateOfBirth();
        checkUserEmail();
        checkUserPassword();

        return validationResult;
    }

    private void checkUsername() {
        String username = createUserDto.getName();
        if (username.length() < 3 || username.length() > 20) {
            validationResult.add(Error.of("invalid-username", "Username should be at least 3 characters and max 20 characters."));
        }

        if (!username.matches("[a-zA-Z]\\w+")) {
            validationResult.add(Error.of("invalid-char", "Username can contain letter, number and _ First character should be a letter."));
        }
    }

    private void checkUserDateOfBirth() {
        if (!LocalDateTimeFormatter.isDateValid(createUserDto.getBirthday(), "yyyy-MM-dd")) {
            validationResult.add(Error.of("invalid-birthday", "Invalid date format."));
        }
    }

    private void checkUserEmail() {
        if (!createUserDto.getEmail().matches("[a-z][\\w.-]+@[a-z][\\w]+.[a-z]{2,3}")) {
            validationResult.add(Error.of("invalid-email", "Email contains invalid characters. Email should be like test@test.com"));
        }
    }

    private void checkUserPassword() {
        String userPassword = createUserDto.getPassword();
        if (!userPassword.matches("\\S+")) {
            validationResult.add(Error.of("invalid-pass", "Password can't contain spaces."));
        }

        if (userPassword.length() < 5 || userPassword.length() > 30) {
            validationResult.add(Error.of("invalid-pass", "Password should be at least 5 characters and max 30 characters."));
        }

        if (!userPassword.equals(createUserDto.getPasswordConfirmation())) {
            validationResult.add(Error.of("invalid-pass", "Incorrect password."));
        }
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
