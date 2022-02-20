package com.company.service;

import com.company.dao.BetDao;
import com.company.dto.BetDto;
import com.company.dto.LotDto;
import com.company.dto.PlaceBetDto;
import com.company.exception.LotSaleTimeElapsedException;
import com.company.exception.ValidationException;
import com.company.mapper.CreateBetMapper;
import com.company.util.LotCountdown;
import com.company.validator.Error;
import com.company.validator.PlaceBetValidator;
import com.company.validator.ValidationResult;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BetService {

    private static final BetService INSTANCE = new BetService();
    private final CreateBetMapper createBetMapper = CreateBetMapper.getInstance();
    private final BetDao betDao = BetDao.getInstance();
    private final PlaceBetValidator placeBetValidator = PlaceBetValidator.getInstance();
    private final Map<Integer, LotCountdown> lotCountdown = LotService.getLotCountdown();

    public void placeBet(PlaceBetDto placeBetDto) throws SQLException {
        if (!lotCountdown.containsKey(placeBetDto.getLotId())) {
            ValidationResult validationSaleTimeResult = new ValidationResult();
            validationSaleTimeResult.add(Error.of("double-bet", "You can't place a bet after elapsed sale time"));
            throw new LotSaleTimeElapsedException(validationSaleTimeResult.getErrors());
        }

        var validationResult = placeBetValidator.validateData(placeBetDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var betEntity = createBetMapper.mapFrom(placeBetDto);
        betDao.save(betEntity);
    }

    public List<BetDto> getAllBetByLotId(Integer id) {
        return betDao.findById(id).stream().map(betEntity -> BetDto.builder()
                .id(betEntity.getId())
                .lotName(betEntity.getLotName())
                .lotId(betEntity.getLotId())
                .userName(betEntity.getUserName())
                .userBet(betEntity.getUserBet())
                .build()).collect(toList());

    }

    public static BetService getInstance() {
        return INSTANCE;
    }
}
