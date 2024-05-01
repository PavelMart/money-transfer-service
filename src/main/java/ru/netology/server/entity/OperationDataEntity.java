package ru.netology.server.entity;

import lombok.Data;

import java.util.Date;

@Data
public class OperationDataEntity {
    String operationId;
    String cardFromNumber;
    String cardToNumber;
    Amount amount;
    OperationResult result = OperationResult.CONFIRM_PENDING;
    String createdAt = new Date().toString();
    String confirmedAt;
    Double fee = 0.0;

    //    При использовании такого подхода вознкикает ошибка Cannot invoke "java.lang.Double.doubleValue()" because "this.feePercent" is null
    //    @Value("${myapp.fee}")
    //    Double feePercent;

    public OperationDataEntity(TransferEntity transferEntity) {
        this.operationId = transferEntity.getOperationId();
        this.cardFromNumber = transferEntity.getCardFromNumber();
        this.cardToNumber = transferEntity.getCardToNumber();
        this.amount = transferEntity.getAmount();
//        this.fee = transferEntity.getAmount().getValue() * feePercent;
        this.fee = transferEntity.getAmount().getValue() * 0.01;
    }
}
