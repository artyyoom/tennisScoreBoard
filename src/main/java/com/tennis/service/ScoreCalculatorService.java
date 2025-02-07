package com.tennis.service;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.Player;
import lombok.Setter;

@Setter
public class ScoreCalculatorService {

    public static void updateScore(Long winnerId, CurrentMatchDto currentMatch) {
        addPoint(winnerId, currentMatch);

        checkToAddGame(winnerId, currentMatch);

        checkToAddSet(winnerId, currentMatch);
    }

    private static void addPoint(Long winnerId, CurrentMatchDto currentMatch) {
        Player firstPlayer = currentMatch.getFirstPlayer();

        if (firstPlayer.getId().equals(winnerId)) {
            currentMatch.getFirstScore().addPoint();
        }
        else {
            currentMatch.getSecondScore().addPoint();
        }
    }

    private static void checkToAddGame(Long winnerId, CurrentMatchDto currentMatch) {
        int firstScore = currentMatch.getFirstScore().getPoint();
        int secondScore = currentMatch.getSecondScore().getPoint();

        if (firstScore <= 3 && secondScore <= 3) {return;}

        int totalPoints = firstScore - secondScore;
        equalGameScore(totalPoints, winnerId, currentMatch);
    }

    private static void equalGameScore(int totalPoints, Long winnerId, CurrentMatchDto currentMatch) {
        if (totalPoints >= 2 || totalPoints <= -2) {
            addGame(winnerId, currentMatch);
            delPoints(currentMatch);
        }
    }

    private static void addGame(Long winnerId, CurrentMatchDto currentMatch) {
        Player firstPlayer = currentMatch.getFirstPlayer();

        if (firstPlayer.getId().equals(winnerId)) {
            currentMatch.getFirstScore().addGame();
        }
        else {
            currentMatch.getSecondScore().addGame();
        }
    }

    private static void checkToAddSet(Long winnerId, CurrentMatchDto currentMatch) {
        int firstScore = currentMatch.getFirstScore().getGame();
        int secondScore = currentMatch.getSecondScore().getGame();

        if (firstScore < 5 && secondScore < 5) {return;}
        if (firstScore == 6 && secondScore <= 4) {
            addSet(winnerId, currentMatch);
        }
        if (secondScore == 6 && firstScore <= 4) {
            addSet(winnerId, currentMatch);
        }
        if (firstScore >= 5 && secondScore >= 5) {
            int totalPoints = firstScore - secondScore;
            equalSetScore(totalPoints, winnerId, currentMatch);
        }

//        if (firstScore <= 5 && secondScore <= 5) {return;}
//        if (firstScore >= 6 && secondScore >= 6) {
//            int totalPoints = firstScore - secondScore;
//            equalSetScore(totalPoints, winnerId, currentMatch);
//        }
//        if (firstScore == 6 || secondScore == 6) {
//            addSet(winnerId, currentMatch);
//        }
    }

    private static void equalSetScore(int totalPoints, Long winnerId, CurrentMatchDto currentMatch) {
        if (totalPoints >= 2) {
            addSet(winnerId, currentMatch);
        }
        if (totalPoints <= -2) {
            addSet(currentMatch.getSecondPlayer().getId(), currentMatch);
        }
    }

    private static void addSet(Long winnerId, CurrentMatchDto currentMatch) {
        Player firstPlayer = currentMatch.getFirstPlayer();

        if (firstPlayer.getId().equals(winnerId)) {
            currentMatch.getFirstScore().addSet();
        }
        else {
            currentMatch.getSecondScore().addSet();
        }
        delPoints(currentMatch);
        delGames(currentMatch);
    }

    private static void delPoints(CurrentMatchDto currentMatch) {
        currentMatch.getFirstScore().delPoints();
        currentMatch.getSecondScore().delPoints();
    }

    private static void delGames(CurrentMatchDto currentMatch) {
        currentMatch.getFirstScore().delGames();
        currentMatch.getSecondScore().delGames();
    }
}
