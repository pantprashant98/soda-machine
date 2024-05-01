package com.example.sodamachine.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private static final String LOG_FILE_PATH = "logs/soda-purchases.log";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logPurchase(String sodaName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true))) {
            String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
            String logEntry = String.format("%s,%s%n", timestamp, sodaName);
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}