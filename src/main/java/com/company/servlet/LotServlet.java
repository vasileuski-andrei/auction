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
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet("/lot")
public class LotServlet extends HttpServlet {

    private final BetService betService = BetService.getInstance();
    private Integer lotId;
    private String lotName;
    private String bet;
    private String lastBet;
    private String userPlacedLastBet;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        lotId = Integer.valueOf(req.getParameter("lotId"));
        lotName = req.getParameter("lotName");
        bet = req.getParameter("startBet");
        var allBetByLotId = betService.getAllBetByLotId(lotId);
        userPlacedLastBet  = !allBetByLotId.isEmpty()
                ? allBetByLotId.get(allBetByLotId.size()-1).getUserName() : req.getParameter("lotOwner");
        var reqParamLastBet = req.getParameter("lastBet");
        if (!Objects.equals(reqParamLastBet, "null")) {
            lastBet = reqParamLastBet;
        } else {
            lastBet = bet;
        }

        req.setAttribute("bets", allBetByLotId);
        req.getRequestDispatcher("WEB-INF/jsp/lot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var placeBetDto = PlaceBetDto.builder()
                .lotName(lotName)
                .lotId(lotId)
                .userId(user.getId())
                .userName(user.getName())
                .startBet(Integer.valueOf(bet))
                .lastBet(Integer.valueOf(lastBet))
                .userBet(req.getParameter("userBet"))
                .userPlacedLastBet(userPlacedLastBet)
                .build();

        try {
            betService.placeBet(placeBetDto);
            resp.sendRedirect("/market");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher("WEB-INF/jsp/market.jsp").forward(req, resp);
//            doGet(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
