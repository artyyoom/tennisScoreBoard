package com.tennis.service;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.Match;
import com.tennis.model.Player;

import java.util.UUID;

public class FinishedMatchesPersistenceService {

    MatchService matchService = MatchService.getInstance();

    public Match finishGame(UUID uuid, Long winnerId) {
        CurrentMatchDto currentMatch = CurrentMatchStorage.getCurrentMatch(uuid);

        Player firstPlayer = currentMatch.getFirstPlayer();
        Player secondPlayer = currentMatch.getSecondPlayer();
        Player winner = defineTheWinner(winnerId, firstPlayer, secondPlayer);

        Match match = matchService.saveMatch(firstPlayer, secondPlayer, winner);

        CurrentMatchStorage.delCurrentMatch(uuid);

        return match;
    }

    private Player defineTheWinner(Long winnerId, Player player1, Player player2) {
        if (player1.getId().equals(winnerId)) {
            return player1;
        }
        return player2;
    }
}
