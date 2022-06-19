package com.payconiq.payconiqstockapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "stocks")
public class Stock {
    @Id
    private String id;
    private String name;
    private String currentPrice;

    @LastModifiedDate
    private LocalDateTime lastUpdate;
    private LocalDateTime createdDate;
}
