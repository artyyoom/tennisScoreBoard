package com.tennis.dto;

import com.tennis.model.Player;
import com.tennis.model.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrentMatchDto {
    private Player firstPlayer;
    private Player secondPlayer;
    private Score firstScore;
    private Score secondScore;
}
