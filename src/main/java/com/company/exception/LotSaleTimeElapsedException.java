package com.company.exception;

import com.company.validator.Error;
import lombok.Getter;

import java.util.List;

public class LotSaleTimeElapsedException extends RuntimeException {

    @Getter
    private final List<Error> errors;


    public LotSaleTimeElapsedException(List<Error> errors) {
        this.errors = errors;
    }
}
