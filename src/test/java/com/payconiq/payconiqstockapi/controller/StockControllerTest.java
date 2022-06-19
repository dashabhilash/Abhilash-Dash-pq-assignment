package com.payconiq.payconiqstockapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.payconiqstockapi.dto.StockDTO;
import com.payconiq.payconiqstockapi.service.StockService;
import com.payconiq.payconiqstockapi.service.impl.StockServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("StockController - Unit Test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @MockBean
    MappingMongoConverter mappingMongoConverter;
    @MockBean
    private StockController stockController;
    @MockBean
    private StockService stockService;
    @MockBean
    private StockServiceImpl stockServiceImpl;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getStocks_shouldReturnStatusOk_ifStocksGetRetrievedSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks"))
                .andExpect(status().isOk());
    }

    @Test
    public void getStock_shouldReturnStatusOk_ifStockGetsRetrievedSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createStock_shouldReturnStatusOk_ifStockGetsCreatedSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                        .content(asJsonString(new StockDTO("11", "sampleStock", "1000")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStockPrice_shouldReturnStatusOk_ifStockPriceGetsUpdatedSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/stocks/{id}/{stockPrice}", "1", "2000"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStock_shouldReturnStatusOk_ifStockDeletedSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/stocks/{id}", "1"))
                .andExpect(status().isOk());
    }
}