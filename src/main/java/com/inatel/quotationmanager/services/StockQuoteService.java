package com.inatel.quotationmanager.services;

import com.inatel.quotationmanager.dtos.StockQuoteDTO;

import java.util.List;
import java.util.Optional;

public interface StockQuoteService {

    public Optional<StockQuoteDTO> create(StockQuoteDTO stockQuoteDTO);

    public Optional<StockQuoteDTO> findById(String id);

    public List<StockQuoteDTO> findAll();

    public List<StockQuoteDTO> findByStockId(String stockId);


}
