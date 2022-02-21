package com.company.mapper;

import com.company.dto.CreateLotDto;
import com.company.entity.LotEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateLotMapper implements Mapper <CreateLotDto, LotEntity> {

    private static final CreateLotMapper INSTANCE = new CreateLotMapper();

    @Override
    public LotEntity mapFrom(CreateLotDto object) {
        return LotEntity.builder()
                .lotName(object.getLotName())
                .owner(object.getOwner())
                .startBid(Integer.parseInt(object.getStartBid()))
                .lotStatus(object.getLotStatus())
                .build();
    }

    public static CreateLotMapper getInstance() {
        return INSTANCE;
    }
}
