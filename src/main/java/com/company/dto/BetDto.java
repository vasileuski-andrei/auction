package com.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BetDto {

    private Integer id;
    private String lotName;
    private Integer lotId;
    private String userName;
    private Integer userBet;
}
