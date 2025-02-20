package com.tennis.servlet;

import com.tennis.dto.MatchesPageDto;
import com.tennis.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    MatchService matchService = MatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initialPage = req.getParameter("page");
        String playerNameByFilter = req.getParameter("filter_by_player_name");

        MatchesPageDto matchesForPage = matchService.getMatches(initialPage, playerNameByFilter);

        req.setAttribute("page", matchesForPage.getPage());
        req.setAttribute("totalPages", matchesForPage.getTotalPages());
        req.setAttribute("matches", matchesForPage.getMatchesForPage());
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
