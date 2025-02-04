package com.tennis.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.CurrentMatch;
import com.tennis.service.Service;
import jakarta.servlet.RequestDispatcher;
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

        service.savePlayer(player1);
        service.savePlayer(player2);

        Long idPlayer1 = service.getPlayerIdByName(player1);
        Long idPlayer2 = service.getPlayerIdByName(player2);

        CurrentMatchDto currentMatchDto = new CurrentMatchDto(idPlayer1, idPlayer2, 0, 0);
        UUID uuid = UUID.randomUUID();

        CurrentMatch.addCurrentMatch(uuid, currentMatchDto);

//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("match-score.jsp");
//        requestDispatcher.forward(req, resp);
    }
}
