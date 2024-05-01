package ru.netology.server.logger;

import ru.netology.server.entity.OperationDataEntity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    public static void transferLog(OperationDataEntity operation) throws IOException {
        var builder = new StringBuilder();
        builder.append("[").append(new Date()).append("] ");
        builder.append("Operation ID: ").append(operation.getOperationId()).append(" ");
        builder.append("Result: ").append(operation.getResult()).append("\n");
        builder.append("From card: ").append(operation.getCardFromNumber()).append("\n");
        builder.append("To card: ").append(operation.getCardToNumber()).append("\n");
        builder.append("Value: ").append(operation.getAmount().getValue()).append("\n");
        builder.append("Commission: ").append(operation.getFee()).append("\n");
        builder.append("Currency: ").append(operation.getAmount().getCurrency()).append("\n\n");

        try (
                BufferedWriter logWriter = new BufferedWriter(new FileWriter("file.log", true));
        ) {
            logWriter.write(builder.toString());
            logWriter.flush();
        } catch (IOException e) {
            throw new IOException();
        }
    }
}
