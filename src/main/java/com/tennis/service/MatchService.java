package com.tennis.service;

import com.tennis.model.Match;
import com.tennis.model.Player;
import com.tennis.repository.MatchRepository;
import org.hibernate.Session;

public class MatchService {

    private static MatchService instance;

    private MatchService() {}

    public static MatchService getInstance() {
        if (instance == null) {
            instance = new MatchService();
        }
        return instance;
    }

    MatchRepository matchRepository = MatchRepository.getInstance();

    public Match saveMatch(Player firstPlayer, Player secondPlayer, Player winner) {
        Match match = Match.builder()
                .Player1(firstPlayer)
                .Player2(secondPlayer)
                .Winner(winner)
                .build();
        matchRepository.saveMatch(match);
        return match;
    }
}
