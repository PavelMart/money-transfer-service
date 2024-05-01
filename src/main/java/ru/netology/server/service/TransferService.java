package ru.netology.server.service;

import org.springframework.stereotype.Service;
import ru.netology.server.dto.SuccessDto;
import ru.netology.server.entity.ConfirmOperationEntity;
import ru.netology.server.entity.OperationDataEntity;
import ru.netology.server.entity.OperationResult;
import ru.netology.server.entity.TransferEntity;
import ru.netology.server.exception.InvalidCredentials;
import ru.netology.server.exception.NotFound;
import ru.netology.server.logger.Logger;
import ru.netology.server.repository.TransferRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class TransferService {
    TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public List<OperationDataEntity> getTransferHistory() {
        return transferRepository.getTransferHistory();
    }

    public SuccessDto transfer(TransferEntity transfer) throws RuntimeException, IOException {
        createTransferFromBank(transfer);
        var operation = transferRepository.save(transfer);
        Logger.transferLog(operation);
        return new SuccessDto(operation);
    }

    public SuccessDto confirmOperation(ConfirmOperationEntity confirmOperation) throws RuntimeException, IOException {
        var operation = transferRepository.getTransferOperation(confirmOperation.operationId());

        if (operation == null) {
            throw new NotFound("Операция не найдена");
        }

        confirmOperationFromBank(operation, confirmOperation.code());

        operation.setResult(OperationResult.SUCCESS);
        operation.setConfirmedAt(new Date().toString());

        Logger.transferLog(operation);

        return new SuccessDto(operation);
    }

    public void confirmOperationFromBank(OperationDataEntity operation, String operationCode) throws RuntimeException {
        /*
         * Метод-заглушка
         * Данный метод подтверждает операцию в банке через его api
         */
        if (!operationCode.equals("0000")) {
            throw new InvalidCredentials("Неправильный код подтверждения", operation.getOperationId());
        }
    }

    public void createTransferFromBank(TransferEntity transfer) throws RuntimeException {
        /*
         * Метод-заглушка
         * Регистраци операции в банке через его api
         */
        if (transfer.getCardFromNumber().equals(transfer.getCardToNumber())) {
            throw new InvalidCredentials("Номер карты получателя должен отличаться от номера карты отправителя", transfer.getOperationId());
        }
        if (!transfer.getCardFromCVV().equals("111")) {
            throw new InvalidCredentials("Неправильный CVV код", transfer.getOperationId());
        }
    }

}
