package com.carlos.gamificationservice.dtos.dtosImpl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserScoreRankDTO {

    private String userName;
    private Integer score;
    private Long rank;

    public UserScoreDTO() {
    }
}
