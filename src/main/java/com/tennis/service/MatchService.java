package com.tennis.service;

import com.tennis.dto.MatchesPageDto;
import com.tennis.model.Match;
import com.tennis.model.Player;
import com.tennis.repository.MatchRepository;
import com.tennis.util.DataValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchService {

    private static MatchService instance;

    private MatchService() {}

    public static MatchService getInstance() {
        if (instance == null) {
            instance = new MatchService();
        }
        return instance;
    }

    DataValidator dataValidator = new DataValidator();
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


    public Optional<List<Match>> filterMatchesByPlayer(List<Match> allMatches, String playerName) {
        List<Match> filteredMatches = new ArrayList<>();
        for (Match match : allMatches) {
            if (match.getPlayer1().getName().equals(playerName) || match.getPlayer2().getName().equals(playerName)) {
                filteredMatches.add(match);
            }
        }
        return filteredMatches.isEmpty() ? Optional.empty() : Optional.of(filteredMatches);
    }

    public Optional<MatchesPageDto> getMatches(String initialPage, String playerNameByFilter) {
        final int pageSize = 3;
        int currentPage = parseInitialPage(initialPage);
        Optional<List<Match>> filteredMatches = getFilteredMatches(playerNameByFilter);
        if (filteredMatches.isEmpty()) {
            return Optional.empty();
        }
        List<Match> matchesForCurrentPage = getMatchesForPage(pageSize,currentPage, filteredMatches.get());
        int totalMatches = filteredMatches.get().size();

        return Optional.of(new MatchesPageDto(currentPage, calculateTotalPages(pageSize, totalMatches), matchesForCurrentPage));
    }

    private int parseInitialPage(String initialPage) {
        if (initialPage != null && !initialPage.equals("0")) {
            dataValidator.checkPage(initialPage);
            return Integer.parseInt(initialPage);
        }
        return 1;
    }

    private Optional<List<Match>> getFilteredMatches(String playerNameByFilter) {
        List<Match> allMatches = getAllMatches();

        if (playerNameByFilter == null || playerNameByFilter.isEmpty()) {
            return Optional.of(allMatches);
        }

        return filterMatchesByPlayer(allMatches, playerNameByFilter);
    }

    private int calculateTotalPages(int pageSize, double totalMatches) {
        return (int) Math.ceil(totalMatches / pageSize);
    }

    private List<Match> getMatchesForPage(int pageSize, int page, List<Match> matches) {
        int[] indexes = calculateMatchIndexes(page, pageSize, matches.size());

        try {
            return matches.subList(indexes[0], indexes[1]);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }

    private int[] calculateMatchIndexes(int page, int pageSize, int totalMatches) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalMatches);
        return new int[]{startIndex, endIndex};
    }
}
