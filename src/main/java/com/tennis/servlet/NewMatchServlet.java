package com.tennis.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.dto.CurrentMatchDto;
import com.tennis.exception.InvalidDataException;
import com.tennis.service.CurrentMatchStorage;
import com.tennis.model.Player;
import com.tennis.model.Score;
import com.tennis.service.PlayerService;
import com.tennis.util.DataValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();
    PlayerService playerService = PlayerService.getInstance();
    DataValidator dataValidator = new DataValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1 = req.getParameter("playerOne");
        String player2 = req.getParameter("playerTwo");
        String errorMessage = null;

        try {
            dataValidator.checkNames(player1, player2);
            Player firstPlayer = playerService.savePlayer(player1);
            Player secondPlayer = playerService.savePlayer(player2);

            UUID currentMatchId = UUID.randomUUID();

            CurrentMatchDto currentMatch = new CurrentMatchDto(currentMatchId, firstPlayer, secondPlayer, new Score(), new Score());

            CurrentMatchStorage.addCurrentMatch(currentMatchId, currentMatch);

            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + currentMatchId);
        } catch (Exception e) {
            errorMessage = e.getMessage();
            req.setAttribute("errorMessage", errorMessage);
            req.getRequestDispatcher("new-match.jsp").forward(req, resp);
        }

    }
}
