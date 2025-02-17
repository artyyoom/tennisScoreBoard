package com.tennis.servlet;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.Match;
import com.tennis.service.*;
import com.tennis.util.DataValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    DataValidator dataValidator = new DataValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initialUuid = req.getParameter("uuid");
        dataValidator.checkUuid(initialUuid);
        UUID uuid = UUID.fromString(initialUuid);

        CurrentMatchDto currentMatchDto = CurrentMatchStorage.getCurrentMatch(uuid);

        req.setAttribute("currentMatch", currentMatchDto);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initialUuid = req.getParameter("uuid");
        String initialWinnerId = req.getParameter("winnerId");

        dataValidator.checkUuid(initialUuid);
        UUID uuid = UUID.fromString(initialUuid);

        dataValidator.checkId(initialWinnerId);
        Long winnerId = Long.valueOf(initialWinnerId);


        CurrentMatchDto currentMatch = CurrentMatchStorage.getCurrentMatch(uuid);
        ScoreCalculatorService.updateScore(winnerId, currentMatch);

        int firstScore = currentMatch.getFirstScore().getSet();
        int secondScore = currentMatch.getSecondScore().getSet();
        if (firstScore >= 3 || secondScore >= 3) {
            FinishedMatchesPersistenceService finishedMatch = new FinishedMatchesPersistenceService();
            //TODO получать данные не из finishGame
            Match match = finishedMatch.finishGame(uuid, winnerId);

            req.setAttribute("match", match);
            req.getRequestDispatcher("final-score.jsp").forward(req, resp);
        } else {
            req.setAttribute("currentMatch", currentMatch);
            req.getRequestDispatcher("match-score.jsp").forward(req, resp);
        }
    }
}
