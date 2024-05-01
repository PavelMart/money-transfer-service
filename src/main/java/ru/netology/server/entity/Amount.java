package ru.netology.server.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Amount {
    @Positive
    @NotNull
    private Double value;
    @NotNull
    private String currency;

    public Amount(Double value, String currency) {
        this.value = value;
        this.currency = currency;
    }
}
