package ru.javarush.pastukhov.animalisland.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        builder.append(DATE_TIME_FORMATTER.format(LocalDateTime.now()))
               .append(" | ")
               .append(String.format("%-5s", record.getLevel()))
               .append(" | ")
               .append(record.getMessage())
               .append("\n");
        return builder.toString();
    }
}
