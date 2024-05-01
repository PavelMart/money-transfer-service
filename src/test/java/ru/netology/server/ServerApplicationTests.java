package ru.netology.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.netology.server.dto.SuccessDto;
import ru.netology.server.entity.Amount;
import ru.netology.server.entity.ConfirmOperationEntity;
import ru.netology.server.entity.OperationResult;
import ru.netology.server.entity.TransferEntity;
import ru.netology.server.repository.TransferRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServerApplicationTests {

    Gson gson = new GsonBuilder().serializeNulls().create();

    TransferEntity transferEntity = new TransferEntity(
            "1111222233334444",
            "11/22",
            "111",
            "4444333322221111",
            new Amount(10.0, "RUB")
    );

    @Autowired
    private TransferRepository transferRepository;

    @LocalServerPort
    private int port;

    private static TestRestTemplate restTemplate;

    private static final GenericContainer<?> server = new GenericContainer<>("transfer-app")
            .withExposedPorts(5500);

    @BeforeAll
    public static void setUp() {
        restTemplate = new TestRestTemplate();
        server.start();
    }

    @Test
    public void testGetTransferHistory() {
        transferRepository.save(transferEntity);

        ResponseEntity<String> entity = restTemplate.exchange("http://localhost:" + port + "/all", HttpMethod.GET, null, String.class);

        Assertions.assertEquals(
                gson.toJson(transferRepository.getTransferHistory()),
                entity.getBody()
        );
    }

    @Test
    public void testSaveTransferOperation() {
        var entity = restTemplate.exchange("http://localhost:" + port + "/transfer", HttpMethod.POST, new HttpEntity<>(transferEntity), String.class);

        var result = gson.fromJson(entity.getBody(), SuccessDto.class);

        var operation = transferRepository.getTransferOperation(result.getOperationId());

        Assertions.assertNotNull(operation);
        Assertions.assertEquals(operation.getResult(), OperationResult.CONFIRM_PENDING);
    }

    @Test
    public void testConfirmTransferOperation() {
        var entity = restTemplate.exchange("http://localhost:" + port + "/transfer", HttpMethod.POST, new HttpEntity<>(transferEntity), String.class);

        var result = gson.fromJson(entity.getBody(), SuccessDto.class);

        var operation = transferRepository.getTransferOperation(result.getOperationId());

        var confirmData = new ConfirmOperationEntity(operation.getOperationId(), "0000");

        restTemplate.exchange("http://localhost:" + port + "/confirmOperation", HttpMethod.POST, new HttpEntity<>(confirmData), String.class);

        Assertions.assertEquals(operation.getResult(), OperationResult.SUCCESS);
    }

}
