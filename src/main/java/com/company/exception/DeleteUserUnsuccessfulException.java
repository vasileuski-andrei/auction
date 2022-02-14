package com.company.exception;

import com.company.validator.Error;
import lombok.Getter;

import java.util.List;

public class DeleteUserUnsuccessfulException extends Exception {

    @Getter
    private final List<Error> errors;


    public DeleteUserUnsuccessfulException(List<Error> errors) {
        this.errors = errors;
    }
}
