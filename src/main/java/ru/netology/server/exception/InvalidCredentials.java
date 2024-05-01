package ru.netology.server.exception;

public class InvalidCredentials extends RuntimeException {
    private final String id;

    public InvalidCredentials(String msg, String id) {
        super(msg);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
