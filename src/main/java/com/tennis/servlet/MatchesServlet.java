package com.tennis.servlet;

import com.tennis.model.Match;
import com.tennis.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    MatchService matchService = MatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;

        //TODO сделать отдельный метод для проверки
        String reqPage = req.getParameter("page");
        if (reqPage != null) {
            page = Integer.parseInt(reqPage);
        }

        List<Match> allMatches = matchService.getAllMatches();
        List<Match> filteredMatches;

        String playerName = req.getParameter("filter_by_player_name");
        //TODO сделать отдельный метод для проверки
        if (playerName != null) {
            filteredMatches = matchService.filterMatchesByPlayer(allMatches, playerName);
        }
        else {
            filteredMatches = allMatches;
        }

        int pageSize = 3;
        int totalMatches = filteredMatches.size();
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalMatches);

        List<Match> matchesForPage;
        if (startIndex < totalPages) {
            matchesForPage = filteredMatches.subList(startIndex, endIndex);
        } else {
            matchesForPage = List.of();
        }
    }
}
