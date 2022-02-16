package com.company.validator;

import com.company.dto.PlaceBetDto;
import lombok.NoArgsConstructor;

import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlaceBetValidator implements Validator<PlaceBetDto> {

    private static final PlaceBetValidator INSTANCE = new PlaceBetValidator();
    private static final Map<Integer, List<Integer>> lotIdsAndBiddersIds = new HashMap<>();


    @Override
    public ValidationResult validateData(PlaceBetDto object) {
        var validationResult = new ValidationResult();
        var lotId = object.getLotId();

        if (object.getUserBet() <= object.getStartBet()) {
            validationResult.add(Error.of("invalid-username", "ERROR-PRICE"));
            return validationResult;
        }

        if (lotIdsAndBiddersIds.containsKey(lotId)) {
            var biddersId = lotIdsAndBiddersIds.get(lotId);

            if (Objects.equals(object.getUserId(), biddersId.get(biddersId.size() - 1))) {
                validationResult.add(Error.of("invalid-username", "Nelzia stavit 2 raza podriad"));
            return validationResult;

            } else {
                biddersId.add(object.getUserId());
            }
        } else {
            lotIdsAndBiddersIds.put(lotId, new ArrayList<>());
            var biddersId = lotIdsAndBiddersIds.get(lotId);
            biddersId.add(object.getUserId());
        }

        return validationResult;
    }

    public static PlaceBetValidator getInstance() {
        return INSTANCE;
    }
}
