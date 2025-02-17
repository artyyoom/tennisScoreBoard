package com.tennis.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tennis.exception.DataBaseException;
import com.tennis.exception.DataNotFoundException;
import com.tennis.exception.InvalidDataException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(value = {
        "/matches", "/match-score", "/new-match",
})
public class ExceptionFilter extends HttpFilter {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try{
            super.doFilter(req, res, chain);
        }
        catch (DataBaseException e){
            writeError(e, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, res);
        }
        catch (DataNotFoundException e){
            writeError(e, HttpServletResponse.SC_NOT_FOUND, res);
        }
        catch(InvalidDataException e){
            writeError(e, HttpServletResponse.SC_BAD_REQUEST, res);
        }
    }

    private void writeError(RuntimeException e, int code, HttpServletResponse resp) throws IOException {
        Map<String, String> error= new HashMap<>();
        error.put("message", e.getMessage());
        resp.setStatus(code);
        objectMapper.writeValue(resp.getWriter(), error);
    }
}
