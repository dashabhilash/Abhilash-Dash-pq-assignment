package com.payconiq.payconiqstockapi.service.impl;

import com.payconiq.payconiqstockapi.dto.StockDTO;
import com.payconiq.payconiqstockapi.exception.StockSystemException;
import com.payconiq.payconiqstockapi.model.Stock;
import com.payconiq.payconiqstockapi.repository.StockRepository;
import com.payconiq.payconiqstockapi.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public Map<String, Object> getStocks(int pageNumber, int pageSize) {

        Page<Stock> pageStock = stockRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<Stock> stocks = pageStock.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("stocks", stocks);
        response.put("currentPage", pageStock.getNumber());
        response.put("totalItems", pageStock.getTotalElements());
        response.put("totalPages", pageStock.getTotalPages());
        return response;
    }

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public Stock getStock(String stockId) {
        return stockRepository.findById(stockId).orElseThrow(() -> new StockSystemException("Stock not found for StockId: " + stockId));
    }

    public Stock saveStock(StockDTO stockDTO) {
        if (stockRepository.existsById(stockDTO.getId())) {
            throw new StockSystemException("Stock Already Exist");
        }
        ModelMapper modelMapper = new ModelMapper();
        Stock stock = modelMapper.map(stockDTO, Stock.class);
        stock.setCreatedDate(LocalDateTime.now());
        return stockRepository.insert(stock);
    }

    public Stock updateStock(String stockId, String stockPrice) {
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new StockSystemException("Stock not found for StockId: " + stockId));
        stock.setCurrentPrice(stockPrice);
        return stockRepository.save(stock);
    }

    public String deleteStock(String stockId) {
        if (!stockRepository.existsById(stockId))
            throw new StockSystemException("Stock not found for StockId: " + stockId);
        stockRepository.deleteById(stockId);
        return stockId;
    }

    public void deleteStocks() {
        stockRepository.deleteAll();
    }
}
