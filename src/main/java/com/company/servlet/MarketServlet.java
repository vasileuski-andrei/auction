package com.company.servlet;

import com.company.dto.LotDto;
import com.company.service.BetService;
import com.company.service.LotService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/market")
public class MarketServlet extends HttpServlet {

    private final LotService lotService = LotService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lots", lotService.getAllLot());
        req.getRequestDispatcher("WEB-INF/jsp/market.jsp").forward(req, resp);

    }
}
