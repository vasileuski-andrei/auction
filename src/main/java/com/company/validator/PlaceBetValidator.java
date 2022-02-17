package com.company.validator;

import com.company.dto.PlaceBetDto;
import com.company.exception.ValidationException;
import lombok.NoArgsConstructor;

import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlaceBetValidator implements Validator<PlaceBetDto> {

    private static final PlaceBetValidator INSTANCE = new PlaceBetValidator();
    private static final Map<Integer, List<Integer>> lotIdsAndBiddersIds = new HashMap<>();

    private PlaceBetDto placeBetDto;
    private ValidationResult validationResult;


    @Override
    public ValidationResult validateData(PlaceBetDto object) {
        placeBetDto = object;
        validationResult = new ValidationResult();

        checkInputCharacterInBetField();
        checkDoubleBetInARow();

        return validationResult;
    }

    private void checkDoubleBetInARow() {
        var lotId = placeBetDto.getLotId();
        var userId = placeBetDto.getUserId();

        if (placeBetDto.getUserName().equals(placeBetDto.getUserPlacedLastBet())) {
            validationResult.add(Error.of("double-bet", "You can't place two bets in a row"));
            throw new ValidationException(validationResult.getErrors());
        }

        if (lotIdsAndBiddersIds.containsKey(lotId)) {
            var biddersId = lotIdsAndBiddersIds.get(lotId);

            if (Objects.equals(userId, biddersId.get(biddersId.size() - 1))) {
                validationResult.add(Error.of("double-bet", "You can't place two bets in a row"));
                throw new ValidationException(validationResult.getErrors());

            } else {
                biddersId.add(userId);
            }
        } else {
            lotIdsAndBiddersIds.put(lotId, new ArrayList<>());
            var biddersId = lotIdsAndBiddersIds.get(lotId);
            biddersId.add(userId);
        }

    }

    private void checkInputCharacterInBetField() {
        var strUserBet = placeBetDto.getUserBet();
        Integer userBet = null;

        if (!strUserBet.matches("[1-9]+")) {
            validationResult.add(Error.of("double-bet", "You can use only integer numbers"));
            throw new ValidationException(validationResult.getErrors());
        } else {
            userBet = Integer.valueOf(strUserBet);
        }

        if (userBet <= placeBetDto.getStartBet() || userBet <=placeBetDto.getLastBet()) {
            validationResult.add(Error.of("low-bet", "Your bet should be greater than current bet"));
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public static PlaceBetValidator getInstance() {
        return INSTANCE;
    }
}
