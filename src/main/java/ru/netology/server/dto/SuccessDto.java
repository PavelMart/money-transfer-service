package ru.netology.server.dto;

import ru.netology.server.entity.OperationDataEntity;

public class SuccessDto {
    private final String operationId;

    public SuccessDto(String operationId) {
        this.operationId = operationId;
    }

    public SuccessDto(OperationDataEntity entity) {
        this.operationId = entity.getOperationId();
    }

    public String getOperationId() {
        return operationId;
    }

    @Override
    public String toString() {
        return "SuccessDto{" +
                "operationId='" + operationId + '\'' +
                '}';
    }
}
