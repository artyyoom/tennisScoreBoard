package com.tennis;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.Player;
import com.tennis.model.Score;
import com.tennis.service.ScoreCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ScoreCalculatorServiceTest {

    private CurrentMatchDto currentMatch;

    @BeforeEach
    void initEach() {
        Player firstPlayer = new Player(2L, "firstPlayer");
        Player secondPlayer = new Player(1L, "secondPlayer");
        currentMatch = new CurrentMatchDto(UUID.randomUUID(),
                firstPlayer, secondPlayer, new Score(), new Score());
    }

    @Test
    void whenThePlayerWinsWithAnEqualWinningScore() {
        currentMatch.getFirstScore().setPoint(3);
        currentMatch.getSecondScore().setPoint(3);
        // Добавить очко первому игроку
        ScoreCalculatorService.updateScore(currentMatch.getFirstPlayer().getId(), currentMatch);
        System.out.println(currentMatch.getFirstScore().getPoint() + " " + currentMatch.getSecondScore().getPoint());
        ScoreCalculatorService.updateScore(currentMatch.getFirstPlayer().getId(), currentMatch);
        System.out.println(currentMatch.getFirstScore().getGame() + " " + currentMatch.getFirstScore().getPoint() + " " + currentMatch.getSecondScore().getPoint());
    }

    @Test
    void whenPlayerWinsWithAnUnevenScore() {
        // Первый игрок выигрывает при обычном счете
        currentMatch.getFirstScore().setPoint(3);
        ScoreCalculatorService.updateScore(currentMatch.getFirstPlayer().getId(), currentMatch);
        System.out.println(currentMatch.getFirstScore().getGame() + " " + currentMatch.getFirstScore().getPoint());
    }

    @Test
    void whenDoesTheTieBreakStart() {
        currentMatch.getFirstScore().setGame(6);
        currentMatch.getSecondScore().setGame(6);
        currentMatch.getSecondScore().setPoint(3);
        ScoreCalculatorService.updateScore(currentMatch.getSecondPlayer().getId(), currentMatch);
        System.out.println(currentMatch.getSecondScore().getGame() + " " + currentMatch.getSecondScore().getSet());
        currentMatch.getSecondScore().setPoint(3);
        ScoreCalculatorService.updateScore(currentMatch.getSecondPlayer().getId(), currentMatch);
        System.out.println(currentMatch.getSecondScore().getGame() + " " + currentMatch.getSecondScore().getSet());
    }
}
