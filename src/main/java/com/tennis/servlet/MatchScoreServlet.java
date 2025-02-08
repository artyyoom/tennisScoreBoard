package com.tennis.servlet;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.service.CurrentMatchStorage;
import com.tennis.service.FinishedMatchesPersistenceService;
import com.tennis.service.PlayerService;
import com.tennis.service.ScoreCalculatorService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    PlayerService playerService = PlayerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParameter = req.getParameter("uuid");
        //TODO проверить uuid
        UUID uuid = UUID.fromString(uuidParameter);

        CurrentMatchDto currentMatchDto = CurrentMatchStorage.getCurrentMatch(uuid);

        req.setAttribute("currentMatch", currentMatchDto);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParameter = req.getParameter("uuid");
        String parameterWinnerId = req.getParameter("winnerId");
        //TODO проверить uuid
        UUID uuid = UUID.fromString(uuidParameter);
        //TODO проверить winnerId
        Long winnerId = Long.valueOf(parameterWinnerId);

        CurrentMatchDto currentMatch = CurrentMatchStorage.getCurrentMatch(uuid);
        ScoreCalculatorService.updateScore(winnerId, currentMatch);

        int firstScore = currentMatch.getFirstScore().getSet();
        int secondScore = currentMatch.getSecondScore().getSet();
        if (firstScore >= 3 || secondScore >= 3) {
            FinishedMatchesPersistenceService finishedMatch = new FinishedMatchesPersistenceService();
            finishedMatch.finishGame(uuid, winnerId);
            req.setAttribute("currentMatch", currentMatch);
            req.getRequestDispatcher("final-score.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("currentMatch", currentMatch);
            req.getRequestDispatcher("match-score.jsp").forward(req, resp);
        }
    }
}
