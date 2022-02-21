package com.company.mapper;

import com.company.dto.PlaceBidDto;
import com.company.entity.BidEntity;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CreateBidMapper implements Mapper <PlaceBidDto, BidEntity> {

    private static final CreateBidMapper INSTANCE = new CreateBidMapper();

    @Override
    public BidEntity mapFrom(PlaceBidDto object) {
        return BidEntity.builder()
                .lotName(object.getLotName())
                .lotId(object.getLotId())
                .userName(object.getUserName())
                .userBid(Integer.valueOf(object.getUserBid()))
                .build();
    }

    public static CreateBidMapper getInstance() {
        return INSTANCE;
    }
}
