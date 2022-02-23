package com.company.servlet;

import com.company.dto.PlaceBidDto;
import com.company.dto.UserDto;
import com.company.exception.LotSaleTimeElapsedException;
import com.company.exception.ValidationException;
import com.company.service.BidService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/lot")
public class LotServlet extends HttpServlet {

    private final BidService bidService = BidService.getInstance();
    private Integer lotId;
    private String lotName;
    private String startBid;
    private String lastBid;
    private String userPlacedLastBid;
    private String lotOwner;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        lotId = Integer.valueOf(req.getParameter("lotId"));
        lotName = req.getParameter("lotName");
        startBid = req.getParameter("startBid");
        lotOwner = req.getParameter("lotOwner");
        var allBidByLotId = bidService.getAllBidByLotId(lotId);
        userPlacedLastBid = !allBidByLotId.isEmpty()
                ? allBidByLotId.get(allBidByLotId.size()-1).getUserName() : lotOwner;
        var reqParamLastBet = req.getParameter("lastBid");
        if (!Objects.equals(reqParamLastBet, "null")) {
            lastBid = reqParamLastBet;
        } else {
            lastBid = startBid;
        }

        req.setAttribute("bids", allBidByLotId);
        req.setAttribute("lotName", lotName);
        req.setAttribute("lotOwner", lotOwner);
        req.setAttribute("startBid", startBid);
        req.setAttribute("lastBid", lastBid);
        req.getRequestDispatcher("WEB-INF/jsp/lot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var placeBidDto = PlaceBidDto.builder()
                .lotName(lotName)
                .lotId(lotId)
                .lotOwner(lotOwner)
                .userId(user.getId())
                .userName(user.getName())
                .startBid(Integer.valueOf(startBid))
                .lastBid(Integer.valueOf(lastBid))
                .userBid(req.getParameter("userBid"))
                .userPlacedLastBid(userPlacedLastBid)
                .build();

        try {
            bidService.placeBid(placeBidDto);
            resp.sendRedirect("/market");

        } catch (LotSaleTimeElapsedException e) {
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher("WEB-INF/jsp/market.jsp").forward(req, resp);
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher("WEB-INF/jsp/market.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
