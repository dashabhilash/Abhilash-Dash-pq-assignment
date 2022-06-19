package com.payconiq.payconiqstockapi.service;

import com.payconiq.payconiqstockapi.dto.StockDTO;
import com.payconiq.payconiqstockapi.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StockService {


    Map<String, Object> getStocks(int pageNumber, int pageSize);

    List<Stock> getAllStock();

    Stock getStock(String stockId);

    Stock saveStock(StockDTO stockDTO);

    Stock updateStock(String stockId, String stockPrice);

    String deleteStock(String stockId);

    void deleteStocks();
}
