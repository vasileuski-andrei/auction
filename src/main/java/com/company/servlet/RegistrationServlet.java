package com.company.servlet;

import com.company.dto.CreateUserDto;
import com.company.entity.Role;
import com.company.exception.ValidationException;
import com.company.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .passwordConfirmation(req.getParameter("password-confirm"))
                .role(Role.USER)
                .build();

        try {
            userService.create(userDto);

//            req.getSession().setAttribute("user", userDto);

            String regCode = userService.confirmCreatedAccount(userDto.getEmail());
            resp.sendRedirect("/login");
        } catch (ValidationException e) {
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        } catch (SQLException e) {
            resp.sendRedirect("/registration?invalidCreateUser");
        }

    }
}
