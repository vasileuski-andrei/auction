package com.company.servlet;

import com.company.dto.PlaceBetDto;
import com.company.dto.UserDto;
import com.company.exception.ValidationException;
import com.company.service.BetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/lot")
public class LotServlet extends HttpServlet {

    private final BetService betService = BetService.getInstance();
    private Integer lotId;
    private String lotName;
    private String bet;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        lotId = Integer.valueOf(req.getParameter("lotId"));
        lotName = req.getParameter("lotName");
        bet = req.getParameter("startBet");
        req.getRequestDispatcher("WEB-INF/jsp/lot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var currentBet = Integer.valueOf(req.getParameter("bet"));
        var placeBetDto = PlaceBetDto.builder()
                .lotName(lotName)
                .lotId(lotId)
                .userId(user.getId())
                .userName(user.getName())
                .startBet(Integer.valueOf(bet))
                .userBet(currentBet)
                .build();

        try {
            betService.placeBet(placeBetDto);
            resp.sendRedirect("/market");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
