package com.company.service;

import com.company.dao.LotDao;
import com.company.dto.CreateLotDto;
import com.company.dto.LotDto;
import com.company.entity.LotStatus;
import com.company.exception.ValidationException;
import com.company.mapper.CreateLotMapper;
import com.company.util.LotCountdown;
import com.company.validator.CreateLotValidator;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LotService {

    private static final LotService INSTANCE = new LotService();
    private static final LotDao lotDao = LotDao.getInstance();
    private static final CreateLotMapper createLotMapper = CreateLotMapper.getInstance();
    private static final CreateLotValidator createLotValidator = CreateLotValidator.getInstance();
    private static final Map<Integer, LotCountdown> lotCountdown = new ConcurrentHashMap<>();

    public List<LotDto> getAllLot() {
        return lotDao.findAll().stream().map(lotDao -> LotDto.builder()
                .id(lotDao.getId())
                .lotName(lotDao.getLotName())
                .owner(lotDao.getOwner())
                .lotStatus(String.valueOf(lotDao.getLotStatus()))
                .startBid(String.valueOf(lotDao.getStartBid()))
                .lastBid(String.valueOf(lotDao.getLastBid()))
                .time(getRemainingSaleTime(lotDao.getId()))
                .build()).collect(toList());

    }

    @SneakyThrows
    public void addNewLot(CreateLotDto createLotDto) {
        var validationResult = createLotValidator.validateData(createLotDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var lotEntity = createLotMapper.mapFrom(createLotDto);
        var savedLotEntity = lotDao.save(lotEntity);
        runLotCountdown(createLotDto.getSaleTerm(), savedLotEntity.getId());

    }

    public void updateLotStatus(Integer lotId, LotStatus lotStatus) {
        lotDao.updateLotStatusById(lotId, lotStatus);

    }

    private void runLotCountdown(String saleTerm, Integer lotId) {
        lotCountdown.put(lotId, new LotCountdown(lotId, LocalTime.parse(saleTerm).toSecondOfDay()));

    }

    private String getRemainingSaleTime(Integer lotId) {
        return lotCountdown.get(lotId) != null ? lotCountdown.get(lotId).getSaleRemainingTime() : "-";
    }

    public static LotService getInstance() {
        return INSTANCE;
    }

    public static Map<Integer, LotCountdown> getLotCountdown() {
        return lotCountdown;
    }
}
