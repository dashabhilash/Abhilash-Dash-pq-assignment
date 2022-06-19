package com.payconiq.payconiqstockapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDTO {
    @NotBlank(message = "Id is mandatory")
    private String id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Price is mandatory")
    private String currentPrice;
}

