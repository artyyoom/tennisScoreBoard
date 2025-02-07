package com.tennis.service;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.Player;

public class ScoreCalculatorService {

    public static void updateScore(Long winnerId, CurrentMatchDto currentMatch) {
        addPoint(winnerId, currentMatch);

        checkToAddGame(winnerId, currentMatch);
    }

    public static void addPoint(Long winnerId, CurrentMatchDto currentMatch) {
        Player firstPlayer = currentMatch.getFirstPlayer();

        if (firstPlayer.getId().equals(winnerId)) {
            currentMatch.getFirstScore().addPoint();
        }
        else {
            currentMatch.getSecondScore().addPoint();
        }
    }

    public static void checkToAddGame(Long winnerId, CurrentMatchDto currentMatch) {
        int firstScore = currentMatch.getFirstScore().getPoint();
        int secondScore = currentMatch.getSecondScore().getPoint();

        if (firstScore <= 3 && secondScore <= 3) {return;}

        int totalPoints = firstScore - secondScore;
        equalScore(totalPoints, winnerId, currentMatch);
    }

    public static void equalScore(int totalPoints, Long winnerId, CurrentMatchDto currentMatch) {
        if (totalPoints >= 2 || totalPoints <= -2) {
            addGame(winnerId, currentMatch);
            delPoints(currentMatch);
        }
    }

    public static void addGame(Long winnerId, CurrentMatchDto currentMatch) {
        Player firstPlayer = currentMatch.getFirstPlayer();

        if (firstPlayer.getId().equals(winnerId)) {
            currentMatch.getFirstScore().addGame();
        }
        else {
            currentMatch.getSecondScore().addGame();
        }
    }

    public static void checkToSet(Long winnerId, CurrentMatchDto currentMatch) {

    }

    public static void addSet(Long winnerId, CurrentMatchDto currentMatch) {

    }

    public static void delPoints(CurrentMatchDto currentMatch) {
        currentMatch.getFirstScore().delPoints();
        currentMatch.getSecondScore().delPoints();
    }
}
