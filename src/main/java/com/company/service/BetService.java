package com.company.service;

import com.company.dao.BetDao;
import com.company.dto.PlaceBetDto;
import com.company.exception.ValidationException;
import com.company.mapper.CreateBetMapper;
import com.company.validator.PlaceBetValidator;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BetService {

    private static final BetService INSTANCE = new BetService();
    private final CreateBetMapper createBetMapper = CreateBetMapper.getInstance();
    private final BetDao betDao = BetDao.getInstance();
    private final PlaceBetValidator placeBetValidator = PlaceBetValidator.getInstance();

    public void placeBet(PlaceBetDto placeBetDto) throws SQLException {
        //validation

        var validationResult = placeBetValidator.validateData(placeBetDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var betEntity = createBetMapper.mapFrom(placeBetDto);
        betDao.save(betEntity);
    }

    public void getAllBetByLotId(Integer id) {
//        betDao.findById()

    }

    public static BetService getInstance() {
        return INSTANCE;
    }
}
