package com.company.exception;

import java.sql.SQLException;

public class CreateUserException extends Exception {

    private String detail;

    public CreateUserException(String detail) {
        this.detail = detail;
    }


}
