package ru.netology.server.controller;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.server.dto.SuccessDto;
import ru.netology.server.entity.ConfirmOperationEntity;
import ru.netology.server.entity.OperationDataEntity;
import ru.netology.server.entity.TransferEntity;
import ru.netology.server.service.TransferService;

import java.io.IOException;
import java.util.List;

@Validated
@RestController
@RequestMapping("")
public class TransferController {
    TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/all")
    public List<OperationDataEntity> getTransferHistory() {
        return transferService.getTransferHistory();
    }

    @PostMapping("/transfer")
    public SuccessDto transfer(@Valid @RequestBody TransferEntity transfer) throws IOException {
        return transferService.transfer(transfer);
    }

    @PostMapping("/confirmOperation")
    public SuccessDto confirmOperation(@RequestBody ConfirmOperationEntity confirmOperation) throws IOException {
        return transferService.confirmOperation(confirmOperation);
    }
}
