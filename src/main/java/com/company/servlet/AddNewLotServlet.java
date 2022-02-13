package com.company.servlet;

import com.company.dto.CreateLotDto;
import com.company.dto.UserDto;
import com.company.entity.LotStatus;
import com.company.exception.ValidationException;
import com.company.service.LotService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-new-lot")
public class AddNewLotServlet extends HttpServlet {

    private final LotService lotService = LotService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/add-new-lot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var user = (UserDto) req.getSession().getAttribute("user");
        var createLotDto = CreateLotDto.builder()
                .lotName(req.getParameter("lot"))
                .owner(user.getName())
                .startPrice(req.getParameter("price"))
                .saleTerm(req.getParameter("term"))
                .lotStatus(LotStatus.SELL)
                .build();

        try {
            lotService.addNewLot(createLotDto);
            resp.sendRedirect("/market");
        } catch (Exception e) {
            System.out.println("YES!!!");
            req.setAttribute("errors", e);
            doGet(req, resp);
        }

    }
}
