package com.payconiq.payconiqstockapi.repository;

import com.payconiq.payconiqstockapi.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
}
