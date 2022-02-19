package com.company.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class LocalTimeFormatter {

    private static final String PATTERN = "HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalTime format(String date) {
        return LocalTime.parse(date, FORMATTER);
    }

    public boolean isValid(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }


    }


}
