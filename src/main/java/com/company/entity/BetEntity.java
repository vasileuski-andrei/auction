package com.company.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetEntity {

    private Integer id;
    private String lotName;
    private Integer lotId;
    private String userName;
    private Integer userBet;
}
