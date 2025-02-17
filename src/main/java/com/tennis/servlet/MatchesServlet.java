package com.tennis.servlet;

import com.tennis.model.Match;
import com.tennis.service.MatchService;
import com.tennis.util.DataValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    DataValidator dataValidator = new DataValidator();
    MatchService matchService = MatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;

        String initialPage = req.getParameter("page");
        if (initialPage != null) {
            dataValidator.checkPage(initialPage);
            page = Integer.parseInt(initialPage);
        }

        List<Match> allMatches = matchService.getAllMatches();
        List<Match> filteredMatches;

        String playerName = req.getParameter("filter_by_player_name");

//        matchService.

        //TODO сделать отдельный метод для проверки
        if (playerName == null) {
            filteredMatches = allMatches;
        }
        else {
            filteredMatches = matchService.filterMatchesByPlayer(allMatches, playerName);
            if (filteredMatches.isEmpty()) {
                req.getRequestDispatcher("matches-not-found.jsp").forward(req, resp);
            }
        }

        int pageSize = 3;
        int totalMatches = filteredMatches.size();
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalMatches);
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);

        List<Match> matchesForPage;

        try {
            matchesForPage = filteredMatches.subList(startIndex, endIndex);
        }
        catch (IndexOutOfBoundsException e) {
            req.getRequestDispatcher("matches-not-found.jsp").forward(req, resp);
            return;
        }

//        if (filteredMatches.isEmpty()) {
//            matchesForPage = List.of();
//            //TODO сделать переход на страницу с надписью что матчей нет
//            req.getRequestDispatcher("matches-not-found.jsp").forward(req, resp);
//        } else {
//            matchesForPage = filteredMatches.subList(startIndex, endIndex);
//        }

        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("matches", matchesForPage);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
