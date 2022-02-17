package com.company.validator;

import com.company.dto.BetDto;
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
        var strUserBet = object.getUserBet();
        Integer userBet = null;

        if (!strUserBet.matches("[1-9]+")) {
            validationResult.add(Error.of("double-bet", "You can't place two bets in a row"));
            return validationResult;
        } else {
            userBet = Integer.valueOf(strUserBet);
        }

        if (object.getUserName().equals(object.getUserPlacedLastBet())) {
            validationResult.add(Error.of("double-bet", "You can't place two bets in a row"));
            return validationResult;
        }

        if (userBet <= object.getStartBet() || userBet <=object.getLastBet()) {
            validationResult.add(Error.of("low-bet", "Your bet should be greater than current bet"));
            return validationResult;
        }

        if (lotIdsAndBiddersIds.containsKey(lotId)) {
            var biddersId = lotIdsAndBiddersIds.get(lotId);

            if (Objects.equals(object.getUserId(), biddersId.get(biddersId.size() - 1))) {
                validationResult.add(Error.of("double-bet", "You can't place two bets in a row"));
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
