package com.company.dto;

import com.company.entity.LotStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateBetDto {

    String lotName;
    Integer lotId;
    String userName;
    Integer userBet;
}
