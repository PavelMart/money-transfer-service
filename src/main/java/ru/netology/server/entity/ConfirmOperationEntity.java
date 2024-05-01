package ru.netology.server.entity;

public record ConfirmOperationEntity(
        String operationId,
        String code
) {
}
