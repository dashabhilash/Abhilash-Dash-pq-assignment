package com.payconiq.payconiqstockapi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class StockApiError {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    private StockApiError() {
        timestamp = LocalDateTime.now();
    }

    public StockApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
}
