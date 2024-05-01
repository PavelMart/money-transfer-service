package ru.netology.server.exception;

public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds() {
        super("Недостаточно средств");
    }
}
