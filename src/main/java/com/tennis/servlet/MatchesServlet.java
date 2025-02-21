package com.tennis.servlet;

import com.tennis.dto.MatchesPageDto;
import com.tennis.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    MatchService matchService = MatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initialPage = req.getParameter("page");
        String playerNameByFilter = req.getParameter("filter_by_player_name");

        Optional<MatchesPageDto> matchesForPage = matchService.getMatches(initialPage, playerNameByFilter);

        if (matchesForPage.isEmpty() || matchesForPage.get().getMatchesForPage() == null) {
            req.setAttribute("matchesIsEmpty", true);
        }
        else {
            req.setAttribute("matchesIsEmpty", false);
            req.setAttribute("page", matchesForPage.get().getPage());
            req.setAttribute("totalPages", matchesForPage.get().getTotalPages());
            req.setAttribute("matches", matchesForPage.get().getMatchesForPage());
        }

        req.setAttribute("filter_by_player_name", playerNameByFilter);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
