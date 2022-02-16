package com.company.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BetEntity {

    private String lotName;
    private Integer lodId;
    private String userName;
    private Integer userBet;
}
