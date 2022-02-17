package com.company.mapper;

import com.company.dto.PlaceBetDto;
import com.company.entity.BetEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateBetMapper implements Mapper <PlaceBetDto, BetEntity> {

    private static final CreateBetMapper INSTANCE = new CreateBetMapper();

    @Override
    public BetEntity mapFrom(PlaceBetDto object) {
        return BetEntity.builder()
                .lotName(object.getLotName())
                .lotId(object.getLotId())
                .userName(object.getUserName())
                .userBet(Integer.valueOf(object.getUserBet()))
                .build();
    }

    public static CreateBetMapper getInstance() {
        return INSTANCE;
    }
}
