package com.tennis.service;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.Player;

public class ScoreCalculatorService {

    public static void addPoint(Long winnerId, CurrentMatchDto currentMatch) {
        Player firstPlayer = currentMatch.getFirstPlayer();

        if (firstPlayer.getId().equals(winnerId)) {
            currentMatch.getFirstScore().addPoint();
        }
        else {
            currentMatch.getSecondScore().addPoint();
        }
    }
}
