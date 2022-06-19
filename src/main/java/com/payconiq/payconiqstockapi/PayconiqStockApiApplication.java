package com.payconiq.payconiqstockapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class PayconiqStockApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayconiqStockApiApplication.class, args);
    }
}
