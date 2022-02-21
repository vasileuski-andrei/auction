package com.company.validator;

import com.company.dto.PlaceBidDto;
import com.company.exception.LotSaleTimeElapsedException;
import com.company.exception.ValidationException;
import com.company.service.LotService;
import com.company.util.LotCountdown;
import lombok.NoArgsConstructor;

import java.util.*;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PlaceBidValidator implements Validator<PlaceBidDto> {

    private static final PlaceBidValidator INSTANCE = new PlaceBidValidator();
    private static final Map<Integer, List<Integer>> lotIdsAndBiddersIds = new HashMap<>();
    private final Map<Integer, LotCountdown> lotCountdown = LotService.getLotCountdown();

    private PlaceBidDto placeBidDto;
    private ValidationResult validationResult;


    @Override
    public ValidationResult validateData(PlaceBidDto object) {
        placeBidDto = object;
        validationResult = new ValidationResult();

        checkBidOnOwner();
        checkLotSaleTime();
        checkInputCharacterInBidField();
        checkDoubleBidInARow();

        return validationResult;
    }

    private void checkBidOnOwner() {
        if (placeBidDto.getUserName().equals(placeBidDto.getLotOwner())) {
            validationResult.add(Error.of("owner-bid", "It's your lot. You can't place a bid."));
            throw new LotSaleTimeElapsedException(validationResult.getErrors());
        }
    }

    private void checkLotSaleTime() {
        if (lotCountdown != null && !lotCountdown.containsKey(placeBidDto.getLotId())) {
            validationResult.add(Error.of("double-bid", "You can't place a bet after elapsed sale time"));
            throw new LotSaleTimeElapsedException(validationResult.getErrors());
        }
    }

    private void checkDoubleBidInARow() {
        var lotId = placeBidDto.getLotId();
        var userId = placeBidDto.getUserId();

        if (placeBidDto.getUserName().equals(placeBidDto.getUserPlacedLastBid())) {
            validationResult.add(Error.of("double-bid", "You can't place two bids in a row"));
            throw new ValidationException(validationResult.getErrors());
        }

        if (lotIdsAndBiddersIds.containsKey(lotId)) {
            var biddersId = lotIdsAndBiddersIds.get(lotId);

            if (Objects.equals(userId, biddersId.get(biddersId.size() - 1))) {
                validationResult.add(Error.of("double-bid", "You can't place two bids in a row"));
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

    private void checkInputCharacterInBidField() {
        var strUserBet = placeBidDto.getUserBid();
        Integer userBid;

        if (!strUserBet.matches("[0-9]+")) {
            validationResult.add(Error.of("double-bet", "You can use only integer numbers"));
            throw new ValidationException(validationResult.getErrors());
        } else {
            userBid = Integer.valueOf(strUserBet);
        }

        if (userBid <= placeBidDto.getStartBid() || userBid <= placeBidDto.getLastBid()) {
            validationResult.add(Error.of("low-bet", "Your bid should be greater than current bid"));
            throw new ValidationException(validationResult.getErrors());
        }
    }

    public static PlaceBidValidator getInstance() {
        return INSTANCE;
    }
}
