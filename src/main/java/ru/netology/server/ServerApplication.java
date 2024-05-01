package ru.netology.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
//		var entity = new TransferEntity(
//				"1",
//				"cardFrom",
//				"11/22",
//				"111",
//				"cardTo",
//				new Amount(
//						10,
//						"RUB"
//				)
//		);

        SpringApplication.run(ServerApplication.class, args);
    }

}
