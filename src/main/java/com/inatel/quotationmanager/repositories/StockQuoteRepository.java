package com.inatel.quotationmanager.repositories;

import com.inatel.quotationmanager.entities.StockQuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockQuoteRepository extends JpaRepository<StockQuoteEntity,String> {
}
