package com.payconiq.payconiqstockapi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.payconiqstockapi.dto.StockDTO;
import com.payconiq.payconiqstockapi.exception.StockSystemException;
import com.payconiq.payconiqstockapi.service.StockService;
import com.payconiq.payconiqstockapi.service.impl.StockServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class StockDataCommandLineRunner implements CommandLineRunner {

    private final StockService stockService;

    public StockDataCommandLineRunner(StockServiceImpl stockService) {
        this.stockService = stockService;
    }

    @SuppressWarnings("Convert2Diamond")
    @Override
    public void run(String... args) throws StockSystemException {
        stockService.deleteStocks();
        if (stockService.getAllStock().size() == 0) {
            try {
                TypeReference<List<StockDTO>> typeReference = new TypeReference<List<StockDTO>>() {
                };
                String STOCK_DATA_JSON = "/data/stock-data.json";
                InputStream inputStream = TypeReference.class.getResourceAsStream(STOCK_DATA_JSON);
                List<StockDTO> stockDTOList = new ObjectMapper().readValue(inputStream, typeReference);
                if (stockDTOList != null && !stockDTOList.isEmpty()) {
                    stockDTOList.forEach(stockService::saveStock);
                }
            } catch (Exception ex) {
                throw new StockSystemException("Unable to save stocks data at start up" + ex.getMessage());
            }
        }
    }
}
