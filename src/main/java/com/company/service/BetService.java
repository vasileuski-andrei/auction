package com.company.service;

import com.company.dao.BetDao;
import com.company.dto.CreateBetDto;
import com.company.entity.BetEntity;
import com.company.mapper.CreateBetMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.SQLException;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BetService {

    private static final BetService INSTANCE = new BetService();
    private final CreateBetMapper createBetMapper = CreateBetMapper.getInstance();
    private final BetDao betDao = BetDao.getInstance();

    public void placeBet(CreateBetDto createBetDto) throws SQLException {
        //validation

        var betEntity = createBetMapper.mapFrom(createBetDto);
        betDao.save(betEntity);
    }

    public static BetService getInstance() {
        return INSTANCE;
    }
}
