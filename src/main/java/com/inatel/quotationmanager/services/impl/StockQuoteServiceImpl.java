package com.inatel.quotationmanager.services.impl;

import com.inatel.quotationmanager.dtos.QuotesDTO;
import com.inatel.quotationmanager.dtos.StockQuoteDTO;
import com.inatel.quotationmanager.entities.QuotesEntity;
import com.inatel.quotationmanager.entities.StockQuoteEntity;
import com.inatel.quotationmanager.mappers.StockQuoteMapper;
import com.inatel.quotationmanager.repositories.StockQuoteRepository;
import com.inatel.quotationmanager.services.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Qualifier(value = "StockQuoteService")
public class StockQuoteServiceImpl implements StockQuoteService {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Autowired
    private StockQuoteMapper stockQuoteMapper;

    @Override
    public Optional<StockQuoteDTO> create(StockQuoteDTO stockQuoteDTO) {
        try {
            List<QuotesEntity> quotesEntityList = stockQuoteMapper.dtoToEntityQuotes(extractQuotes(stockQuoteDTO.getQuotes(), stockQuoteDTO.getId()));
            StockQuoteEntity stockQuoteEntity = stockQuoteMapper.dtoToEntityStock(stockQuoteDTO);
            stockQuoteEntity.setQuotes(quotesEntityList);
            return Optional.of(stockQuoteMapper.entityToDtoStock(stockQuoteRepository.save(stockQuoteEntity)));
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<StockQuoteDTO> findById(String id) {

        try {
            if(stockQuoteRepository.findById(id).isPresent()){
                return Optional.of(stockQuoteMapper.entityToDtoStock(stockQuoteRepository.findById(id).get()));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return Optional.empty();
        }

    }


    @Override
    public List<StockQuoteDTO> findAll() {

        try{
            return stockQuoteMapper.entityToDtoStockList(stockQuoteRepository.findAll());
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return new ArrayList<>();
        }
    }


    @Override
    public Optional<StockQuoteDTO> findByStockId(String id) {

        try {
            if(stockQuoteRepository.findById(id).isPresent()){
                return Optional.of(stockQuoteMapper.entityToDtoStock(stockQuoteRepository.findById(id).get()));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
            return Optional.empty();
        }

    }


    private List<QuotesDTO> extractQuotes(Map<String,String> quotesMap, String id){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return quotesMap.entrySet().stream().map(m -> {
            QuotesDTO quotesDTO = new QuotesDTO();
            Date date = null;
            try {
                date = format.parse(m.getKey());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            quotesDTO.setId(id);
            quotesDTO.setQuote_date(date);
            quotesDTO.setQuote_value(new BigDecimal(m.getValue().toString()));
            return quotesDTO;
        }).collect(Collectors.toList());
    }

}
