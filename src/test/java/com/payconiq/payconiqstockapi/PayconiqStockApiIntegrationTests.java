package com.payconiq.payconiqstockapi;

import com.payconiq.payconiqstockapi.controller.StockController;
import com.payconiq.payconiqstockapi.dto.StockDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PayconiqStockApiApplication - Integration Test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PayconiqStockApiIntegrationTests {

    @Autowired
    StockController stockController;

    @Test
    public void testGetStock() {
        ResponseEntity<?> response = stockController.getStock("1");
        assertAll("getStock() response assertion",
                () -> assertNotNull(response, "Response is null"),
                () -> assertTrue(response.getStatusCode() == HttpStatus.OK, "Response status is not OK"));
    }

    @Test
    public void testGetStocks() {
        ResponseEntity<?> response = stockController.getStocks(0, 5);
        assertAll("getStocks() response assertion",
                () -> assertNotNull(response, "Response is null"),
                () -> assertTrue(response.getStatusCode() == HttpStatus.OK, "Response status is not OK"));
    }

    @Test
    public void testCreateStock() {
        StockDTO stockDTO = new StockDTO("12", "SampleStock", "9000");
        ResponseEntity<?> response = stockController.createStock(stockDTO);
        assertAll("createStock() response assertion",
                () -> assertNotNull(response, "Response is null"),
                () -> assertTrue(response.getStatusCode() == HttpStatus.OK, "Response status is not OK"));
    }

    @Test
    public void testUpdateStockPrice() {
        ResponseEntity<?> response = stockController.updateStockPrice("1", "9999");
        assertAll("updateStockPrice() response assertion",
                () -> assertNotNull(response, "Response is null"),
                () -> assertTrue(response.getStatusCode() == HttpStatus.OK, "Response status is not OK"));
    }

    @Test
    public void testDeleteStock() {
        ResponseEntity<?> response = stockController.deleteStock("9");
        assertAll("deleteStock() response assertion",
                () -> assertNotNull(response, "Response is null"),
                () -> assertTrue(response.getStatusCode() == HttpStatus.OK, "Response status is not OK"));
    }

}
