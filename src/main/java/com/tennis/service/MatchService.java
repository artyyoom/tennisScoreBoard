package com.tennis.service;

import com.tennis.model.Match;
import com.tennis.model.Player;
import com.tennis.repository.MatchRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

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

    public List<Match> getAllMatches() {
        return matchRepository.getMatches();
    }

    public List<Match> filterMatchesByPlayer(List<Match> allMatches, String playerName) {
        List<Match> filteredMatches = new ArrayList<>();
        for (Match match : allMatches) {
            if (match.getPlayer1().getName().equals(playerName) || match.getPlayer2().getName().equals(playerName)) {
                filteredMatches.add(match);
            }
        }
        return filteredMatches;
    }


}
