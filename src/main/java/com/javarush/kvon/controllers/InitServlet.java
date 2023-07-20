package com.javarush.kvon.controllers;

import com.javarush.kvon.services.QuestService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "InitServlet", value="/start")
@Log4j2
public class InitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(true);
        String ipAddress = req.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = req.getRemoteAddr();
        }
        session.setAttribute("name", req.getParameter("name"));
        session.setAttribute("ipAddress", ipAddress);
        session.setAttribute("numberOfPlayedGames", 0);
        session.setAttribute("numberOfGamesWon", 0);
        session.setAttribute("quest", new QuestService());
        log.info("Parameters for sessionID: " + session.getId() + " were initialized.\n Quest started.");
        resp.sendRedirect("/quest");
    }
}
