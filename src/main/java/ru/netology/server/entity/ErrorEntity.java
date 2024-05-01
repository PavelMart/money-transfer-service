package ru.netology.server.entity;

import lombok.Value;

@Value
public class ErrorEntity {
    String message;
    String id;

    public ErrorEntity(String message) {
        this.message = message;
        this.id = null;
    }

    public ErrorEntity(String message, String id) {
        this.message = message;
        this.id = id;
    }

}
