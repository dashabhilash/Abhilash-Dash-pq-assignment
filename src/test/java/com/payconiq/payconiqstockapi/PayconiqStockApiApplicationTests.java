package com.payconiq.payconiqstockapi;

import com.payconiq.payconiqstockapi.controller.StockController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DisplayName("PayconiqStockApiApplication - System Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PayconiqStockApiApplicationTests {

    @Autowired
    StockController stockController;

    @Test
    void contextLoads() {
        Assertions.assertThat(stockController).isNotNull();
    }

}
