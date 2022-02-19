package com.company.validator;

import com.company.dto.CreateLotDto;
import com.company.util.LocalDateFormatter;
import com.company.util.LocalTimeFormatter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateLotValidator implements Validator<CreateLotDto> {

    private static final CreateLotValidator INSTANCE = new CreateLotValidator();

    private CreateLotDto createLotDto;
    private ValidationResult validationResult;

    @Override
    public ValidationResult validateData(CreateLotDto object) {
        createLotDto = object;
        validationResult = new ValidationResult();

        checkLotName();
        checkStartBet();
        checkSaleTerm();

        return validationResult;
    }

    private void checkLotName() {
        String lotName = createLotDto.getLotName();
        if (lotName.length() < 2 || lotName.length() > 20) {
            validationResult.add(Error.of("invalid-lotname", "Lot name should be at least 3 characters and max 20 characters."));
        }

        if (!lotName.matches("[a-z]\\w+")) {
            validationResult.add(Error.of("invalid-char", "Lot name can contain letter, number and _ "));
        }
    }

    private void checkStartBet() {
        var startBet = createLotDto.getStartBet();
        if (!startBet.matches("\\d+") || Integer.parseInt(startBet) <= 0) {
            validationResult.add(Error.of("invalid-bet", "Start bet should be not negative integer number"));
        }
    }

    private void checkSaleTerm() {
        if (!LocalTimeFormatter.isValid(createLotDto.getSaleTerm())) {
            validationResult.add(Error.of("invalid-time", "Invalid time format."));
        }
    }

    public static CreateLotValidator getInstance() {
        return INSTANCE;
    }
}