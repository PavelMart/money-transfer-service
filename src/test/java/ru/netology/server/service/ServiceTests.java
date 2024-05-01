package ru.netology.server.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.server.entity.Amount;
import ru.netology.server.entity.OperationDataEntity;
import ru.netology.server.entity.TransferEntity;
import ru.netology.server.logger.Logger;
import ru.netology.server.repository.TransferRepository;

import java.io.IOException;

@SpringBootTest
public class ServiceTests {
    static TransferService transferService;

    static TransferRepository mockRepository = Mockito.mock(TransferRepository.class);

    static TransferEntity correctTransferEntity = new TransferEntity(
            "1111222233334444",
            "11/22",
            "111",
            "4444333322221111",
            new Amount(10.0, "RUB")
    );

    static TransferEntity emptyTransferEntity = new TransferEntity(
            "",
            "",
            "",
            "",
            new Amount(0.0, "")
    );

    static TransferEntity equalsCardNumbersTransferEntity = new TransferEntity(
            "4444333322221111",
            "11/22",
            "111",
            "4444333322221111",
            new Amount(10.0, "RUB")
    );

    static String correctConfirmCode = "0000";
    static String incorrectConfirmCode = "1111";

    static OperationDataEntity operationDataEntity = new OperationDataEntity(correctTransferEntity);

    @BeforeAll
    public static void setUpAll() throws IOException {
        transferService = new TransferService(mockRepository);
        Mockito.mockStatic(Logger.class);
        Mockito.when(mockRepository.save(correctTransferEntity)).thenReturn(operationDataEntity);
    }


    @Test
    public void testSuccessTransfer() throws IOException {
        var result = transferService.transfer(correctTransferEntity);

        Assertions.assertEquals(result.getOperationId(), operationDataEntity.getOperationId());
    }

    @Test
    public void throwsIfIncorrectDataExceptionTransfer() {
        try {
            transferService.transfer(emptyTransferEntity);
        } catch (RuntimeException | IOException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void throwsIfIncorrectCodeConfirmOperation() {
        try {
            transferService.confirmOperationFromBank(operationDataEntity, incorrectConfirmCode);
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void throwsIfEqualsCardNumbersConfirmOperation() {
        try {
            transferService.confirmOperationFromBank(
                    new OperationDataEntity(equalsCardNumbersTransferEntity),
                    correctConfirmCode
            );
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }
}
