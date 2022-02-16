package com.company.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlaceBetDto {

    String lotName;
    Integer lotId;
    Integer userId;
    String userName;
    Integer startBet;
    Integer userBet;
}
