package com.tennis.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.CurrentMatchStorage;
import com.tennis.model.Player;
import com.tennis.model.Score;
import com.tennis.service.Service;
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
    Service service = Service.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO validate
        String player1 = req.getParameter("player1");
        String player2 = req.getParameter("player2");

        Player firstPlayer = service.savePlayer(player1);
        Player secondPlayer = service.savePlayer(player2);

        CurrentMatchDto currentMatchDto = new CurrentMatchDto(firstPlayer, secondPlayer, new Score(), new Score());
        UUID matchId = UUID.randomUUID();

        CurrentMatchStorage.addCurrentMatch(matchId, currentMatchDto);

        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchId);
    }
}
