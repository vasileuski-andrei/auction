package com.company.servlet;

import com.company.dto.UserDto;
import com.company.exception.DeleteUserUnsuccessfulException;
import com.company.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;

import static com.company.util.UrlPath.*;

@WebServlet(DELETE_USER)
public class DeleteUserServlet extends HttpServlet {

    UserService userService = UserService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var user = (UserDto) req.getSession().getAttribute("user");
        var userId = user.getId();

        try {

            if (req.getParameter("password").equals(userService.getPassById(userId))) {

                userService.delete(userId);
                req.getSession().invalidate();
                resp.sendRedirect(INDEX);
            } else {
                resp.sendRedirect(PROFILE + "?incorrectPassword");
            }

        } catch (SQLException e) {
            resp.sendRedirect(PROFILE + "?sqlexception");
        }


    }
}
