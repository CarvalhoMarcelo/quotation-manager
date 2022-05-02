package com.inatel.quotationmanager.repositories;

import com.inatel.quotationmanager.entities.StockQuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockQuoteRepository extends JpaRepository<StockQuoteEntity,String> {
    List<StockQuoteEntity> findAllByStockId(String id);
}
