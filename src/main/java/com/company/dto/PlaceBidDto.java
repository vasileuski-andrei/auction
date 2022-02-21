package com.company.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceBidDto {

    private String lotName;
    private Integer lotId;
    private String lotOwner;
    private Integer userId;
    private String userName;
    private Integer startBid;
    private Integer lastBid;
    private String userBid;
    private String userPlacedLastBid;
}
