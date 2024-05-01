package ru.netology.server.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class TransferEntity {
    String operationId = createOperationId();
    @NotEmpty(message = "Номер карты отправителя не может быть пустым")
    @Size(min = 16, max = 16, message = "Номер карты отправителя должен состоять из 16 цифр")
    String cardFromNumber;
    String cardFromValidTill;
    @NotEmpty(message = "CVV не может быть пустым")
    @Size(min = 3, max = 3, message = "CVV должен состоять из 3 цифр")
    String cardFromCVV;
    @NotEmpty(message = "Номер карты получателя не может быть пустым")
    @Size(min = 16, max = 16, message = "Номер карты получателя должен состоять из 16 цифр")
    String cardToNumber;
    @NotNull(message = "Значение amount обязательно")
    Amount amount;

    private String createOperationId() {
        var id = (int) (Math.random() * 10000) + 1000;
        return String.valueOf(id);
    }
}
