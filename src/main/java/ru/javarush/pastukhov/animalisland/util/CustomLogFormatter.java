package ru.javarush.pastukhov.animalisland.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    private static final ZoneId ZONE_ID = ZoneId.systemDefault();

    @Override
    public String format(LogRecord record) {
        LocalDateTime time = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(record.getMillis()),
                ZONE_ID
        );

        String level = record.getLevel().getName();
        String message = record.getMessage();
        if (message == null) message = "<no message>";

        StringBuilder builder = new StringBuilder();
        builder.append(DATE_TIME_FORMATTER.format(time))
                .append(" | ")
                .append(String.format("%-5s", level))
                .append(" | ")
                .append(message)
                .append("\n");

        return builder.toString();
    }
}