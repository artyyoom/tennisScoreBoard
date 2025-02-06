package com.tennis.dto;

import com.tennis.model.Player;
import com.tennis.model.Score;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class CurrentMatchDto {
    private UUID uuid;
    private Player firstPlayer;
    private Player secondPlayer;
    private Score firstScore;
    private Score secondScore;
}
