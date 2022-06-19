package com.payconiq.payconiqstockapi.service.impl;

import com.payconiq.payconiqstockapi.dto.StockDTO;
import com.payconiq.payconiqstockapi.exception.StockSystemException;
import com.payconiq.payconiqstockapi.model.Stock;
import com.payconiq.payconiqstockapi.repository.StockRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("StockService - Unit Test")
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @InjectMocks
    StockServiceImpl stockServiceImpl;

    @Mock
    StockRepository stockRepository;

    @Mock
    Page<Stock> pageStock;

    @Test
    void getStocks_shouldCallFindAllOnce_whenMethodGetsInvoked() {
        when(stockRepository.findAll(any(Pageable.class))).thenReturn(pageStock);
        Map<String, Object> response = stockServiceImpl.getStocks(0, 5);

        verify(stockRepository, times(1)).findAll(any(Pageable.class));
        assertAll("stock response assertion",
                () -> assertNotNull(response, "Response is null"),
                () -> assertFalse(response.isEmpty(), "Response is Empty"),
                () -> assertTrue(response.size() == 4, "Response is returning wrong data"));
    }

    @Test
    void getAllStock_shouldCallFindAllOnce_whenMethodGetsInvoked() {
        stockServiceImpl.getAllStock();
        verify(stockRepository, times(1)).findAll();

    }

    @Test
    void getStock_shouldReturnStock_ifStockIsFound() {
        Stock stock = new Stock();
        when(stockRepository.findById(anyString())).thenReturn(Optional.of(stock));
        assertEquals(stockServiceImpl.getStock("valid_stock_id"), stock);
    }

    @Test
    void getStock_shouldThrowStockSystemException_ifStockIdIsNull() {
        when(stockRepository.findById(null)).thenReturn(Optional.empty());

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.getStock(null),
                "Expected getStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock not found for StockId: " + null));
    }

    @Test
    void getStock_shouldThrowStockSystemException_ifStockIsNotFound() {
        when(stockRepository.findById(anyString())).thenReturn(Optional.empty());

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.getStock("invalid_stock_id"),
                "Expected getStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock not found for StockId: invalid_stock_id"));
    }

    @Test
    void saveStock_shouldReturnSavedStock_ifStockGetsSavedSuccessfully() {
        Stock stock = new Stock();
        StockDTO stockDTO = new StockDTO("1", "stock1", "1000");
        when(stockRepository.existsById(anyString())).thenReturn(false);
        when(stockRepository.insert(any(Stock.class))).thenReturn(stock);

        assertNotNull(stockServiceImpl.saveStock(stockDTO));
        verify(stockRepository, times(1)).insert(any(Stock.class));
    }

    @Test
    void saveStock_shouldThrowStockSystemException_ifStockIsAlreadyExist() {
        StockDTO stockDTO = new StockDTO("1", "stock1", "1000");
        when(stockRepository.existsById(anyString())).thenReturn(true);

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.saveStock(stockDTO),
                "Expected saveStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock Already Exist"));
    }

    @Test
    void updateStock_shouldReturnUpdatedStock_ifStockGetsUpdatedSuccessfully() {
        Stock stock = new Stock();
        when(stockRepository.findById(anyString())).thenReturn(Optional.of(stock));
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        assertEquals(stockServiceImpl.updateStock("valid_stock_id", "new_stock_price"), stock);
    }

    @Test
    void updateStock_shouldThrowStockSystemException_ifStockIdIsNull() {
        when(stockRepository.findById(null)).thenReturn(Optional.empty());

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.updateStock(null, "new_stock_price"),
                "Expected updateStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock not found for StockId: " + null));
    }

    @Test
    void updateStock_shouldThrowStockSystemException_ifStockIsNotFound() {
        when(stockRepository.findById(anyString())).thenReturn(Optional.empty());

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.updateStock("invalid_stock_id", "new_stock_price"),
                "Expected updateStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock not found for StockId: invalid_stock_id"));
    }

    @Test
    void deleteStock_shouldReturnStockId_ifStockGetsDeletedSuccessfully() {
        when(stockRepository.existsById(anyString())).thenReturn(true);
        doNothing().when(stockRepository).deleteById(anyString());

        assertEquals(stockServiceImpl.deleteStock("valid_stock_id"), "valid_stock_id");
    }

    @Test
    void deleteStock_shouldThrowStockSystemException_ifStockIdIsNull() {
        when(stockRepository.existsById(null)).thenReturn(false);

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.deleteStock(null),
                "Expected deleteStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock not found for StockId: " + null));
    }

    @Test
    void deleteStock_shouldThrowStockSystemException_ifStockIsNotFound() {
        when(stockRepository.existsById(anyString())).thenReturn(false);

        StockSystemException thrown = assertThrows(
                StockSystemException.class,
                () -> stockServiceImpl.deleteStock("invalid_stock_id"),
                "Expected deleteStock() to throw StockSystemException, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Stock not found for StockId: invalid_stock_id"));
    }

    @Test
    void deleteStocks_shouldCallDeleteAllOnce_whenMethodGetsInvoked() {
        stockServiceImpl.deleteStocks();
        verify(stockRepository, times(1)).deleteAll();

    }
}