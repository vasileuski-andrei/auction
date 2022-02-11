package com.company.service;

import com.company.dao.LotDao;
import com.company.dto.LotDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LotService {

    private static final LotService INSTANCE = new LotService();
    private static final LotDao lotDao = LotDao.getInstance();

    public List<LotDto> getAllLot() {
        return lotDao.findAll().stream().map(lotDao -> LotDto.builder()
                .id(lotDao.getId())
                .name(lotDao.getName())
                .build()).collect(toList());

    }

    public static LotService getInstance() {
        return INSTANCE;
    }
}
