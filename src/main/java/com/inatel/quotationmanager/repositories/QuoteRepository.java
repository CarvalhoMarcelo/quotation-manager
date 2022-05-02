package com.inatel.quotationmanager.repositories;

import com.inatel.quotationmanager.entities.QuotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<QuotesEntity,String> {
    List<QuotesEntity> findAllByIdStock(String idStock);
}
