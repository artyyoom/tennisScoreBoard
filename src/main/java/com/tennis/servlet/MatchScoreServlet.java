package com.tennis.servlet;

import com.tennis.dto.CurrentMatchDto;
import com.tennis.model.CurrentMatchStorage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuidParameter = req.getParameter("uuid");
        //TODO проверить uuid
        UUID uuid = UUID.fromString(uuidParameter);

        CurrentMatchDto currentMatchDto = CurrentMatchStorage.getCurrentMatch(uuid);

        req.setAttribute("uuid", uuid);
        req.setAttribute("currentMatch", currentMatchDto);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
