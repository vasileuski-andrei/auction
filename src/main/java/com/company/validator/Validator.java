package com.company.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
