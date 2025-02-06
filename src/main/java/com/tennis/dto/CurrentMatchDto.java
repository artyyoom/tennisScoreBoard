package com.tennis.dto;

import com.tennis.model.Player;
import com.tennis.model.Score;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CurrentMatchDto {
    private final Player firstPlayerId;
    private final Player secondPlayerId;
    private Score scoreFirstPlayer;
    private Score scoreSecondPlayer;
}
