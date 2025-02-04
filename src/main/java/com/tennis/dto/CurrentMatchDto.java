package com.tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentMatchDto {
    private Long idPlayer1;
    private Long idPlayer2;
    private int scorePlayer1;
    private int scorePlayer2;
}
