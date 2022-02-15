package com.company.validator;

public interface Validator<T> {

    ValidationResult validateData(T object);
}
