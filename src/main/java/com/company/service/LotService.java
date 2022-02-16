package com.company.service;

import com.company.dao.LotDao;
import com.company.dto.CreateLotDto;
import com.company.dto.CreateUserDto;
import com.company.dto.LotDto;
import com.company.entity.LotEntity;
import com.company.exception.ValidationException;
import com.company.mapper.CreateLotMapper;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

import static java.util.stream.Collectors.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LotService {

    private static final LotService INSTANCE = new LotService();
    private static final LotDao lotDao = LotDao.getInstance();
    private static final CreateLotMapper createLotMapper = CreateLotMapper.getInstance();
    private final BetService betService = BetService.getInstance();

    public List<LotDto> getAllLot() {
        return lotDao.findAll().stream().map(lotDao -> LotDto.builder()
                .id(lotDao.getId())
                .lotName(lotDao.getLotName())
                .owner(lotDao.getOwner())
                .lotStatus(String.valueOf(lotDao.getLotStatus()))
                .startPrice(String.valueOf(lotDao.getStartPrice()))
                .lastPrice(String.valueOf(lotDao.getLastPrice()))
                .build()).collect(toList());

    }

    @SneakyThrows
    public void addNewLot(CreateLotDto createLotDto) {
        var lotEntity = createLotMapper.mapFrom(createLotDto);
        lotDao.save(lotEntity);

    }

    public static LotService getInstance() {
        return INSTANCE;
    }
}
