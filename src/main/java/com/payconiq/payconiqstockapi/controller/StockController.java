package com.payconiq.payconiqstockapi.controller;

import com.payconiq.payconiqstockapi.dto.StockDTO;
import com.payconiq.payconiqstockapi.endpoints.StockEndpoints;
import com.payconiq.payconiqstockapi.model.Stock;
import com.payconiq.payconiqstockapi.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockController {
    private final StockService stockService;

    @GetMapping(path = StockEndpoints.STOCKS)
    public ResponseEntity<Map<String, Object>> getStocks(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize) {
        return new ResponseEntity<>(stockService.getStocks(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(path = StockEndpoints.STOCK)
    public ResponseEntity<?> getStock(@Valid @PathVariable("id") String stockId) {
        log.info("StockController: " + stockId);
        Stock stock = stockService.getStock(stockId);
        return ResponseEntity.ok(stock);
    }

    @PostMapping(path = StockEndpoints.CREATE_STOCK)
    public ResponseEntity<?> createStock(@RequestBody StockDTO stockDTO) {
        log.info("StockController: " + stockDTO);
        Stock stock = stockService.saveStock(stockDTO);

        return ResponseEntity.ok(stock);
    }

    @PatchMapping(path = StockEndpoints.UPDATE_STOCK_PRICE)
    public ResponseEntity<?> updateStockPrice(@PathVariable("id") String stockId, @PathVariable("stockPrice") String stockPrice) {
        log.info("StockController: " + stockId);
        Stock stock = stockService.updateStock(stockId, stockPrice);

        return ResponseEntity.ok(stock);
    }

    @DeleteMapping(path = StockEndpoints.DELETE_STOCK)
    public ResponseEntity<?> deleteStock(@PathVariable("id") String stockId) {
        log.info("StockController: " + stockId);
        String result = stockService.deleteStock(stockId);

        return ResponseEntity.ok(result);
    }

}
