package ru.netology.server.repository;

import org.springframework.stereotype.Repository;
import ru.netology.server.entity.OperationDataEntity;
import ru.netology.server.entity.TransferEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TransferRepository {
    private final CopyOnWriteArrayList<OperationDataEntity> transferHistory = new CopyOnWriteArrayList<>();

    public OperationDataEntity save(TransferEntity transferEntity) {
        var operation = new OperationDataEntity(transferEntity);
        transferHistory.add(operation);
        return operation;
    }

    public List<OperationDataEntity> getTransferHistory() {
        return transferHistory;
    }

    public OperationDataEntity getTransferOperation(String operationId) {
        final var operation = transferHistory.stream().filter(o -> o.getOperationId().equals(operationId)).findFirst();
        return operation.orElse(null);
    }
}
