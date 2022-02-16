package com.company.servlet;

import com.company.dto.CreateBetDto;
import com.company.dto.CreateUserDto;
import com.company.dto.UserDto;
import com.company.service.BetService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;

import static com.company.util.UrlPath.LOT;

@WebServlet("/lot")
public class LotServlet extends HttpServlet {

    private final BetService betService = BetService.getInstance();
    private Integer lotId;
    private String lotName;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        lotId = Integer.valueOf(req.getParameter("lotId"));
        lotName = req.getParameter("lotName");
        req.getRequestDispatcher("WEB-INF/jsp/lot.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var bet = Integer.valueOf(req.getParameter("bet"));
        var createBetDto = CreateBetDto.builder()
                .lotName(lotName)
                .lotId(lotId)
                .userName(user.getName())
                .userBet(bet)
                .build();
        betService.placeBet(createBetDto);


    }
}
