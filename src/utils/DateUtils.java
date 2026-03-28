package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtils {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String today() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(formatter);
    }

    public static boolean isValidDate(String input) {
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    public static String formatDisplay(String input) {
        try {
            LocalDate date = LocalDate.parse(input, formatter);

            String day = capitalize(date.getDayOfWeek().toString());
            String month = capitalize(date.getMonth().toString());

            return day + ", " + date.getDayOfMonth() + " " + month + " " + date.getYear();
        } catch (DateTimeParseException ex) {
            return input;
        }
    }

    private static String capitalize(String text) {
        return text.charAt(0) + text.substring(1).toLowerCase();
    }
}