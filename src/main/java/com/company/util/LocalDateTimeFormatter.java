package com.company.util;

import lombok.experimental.UtilityClass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
public class LocalDateTimeFormatter {
    
    public boolean isDateValid(String date, String pattern) {
        try {
            convertStringToLocalDate(date, pattern);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isTimeValid(String date, String pattern) {
        try {
            convertStringToLocalTime(date, pattern);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String convertLocalDateTimeToString(LocalDateTime time, String format) {
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    public LocalDate convertStringToLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public LocalTime convertStringToLocalTime(String date, String pattern) {
        return LocalTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }


}
