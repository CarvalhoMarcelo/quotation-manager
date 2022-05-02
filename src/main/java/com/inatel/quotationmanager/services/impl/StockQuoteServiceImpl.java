package com.inatel.quotationmanager.services.impl;

import com.inatel.quotationmanager.dtos.QuotesDTO;
import com.inatel.quotationmanager.dtos.StockQuoteDTO;
import com.inatel.quotationmanager.entities.QuotesEntity;
import com.inatel.quotationmanager.entities.StockQuoteEntity;
import com.inatel.quotationmanager.mappers.StockQuoteMapper;
import com.inatel.quotationmanager.repositories.QuoteRepository;
import com.inatel.quotationmanager.repositories.StockQuoteRepository;
import com.inatel.quotationmanager.services.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class StockQuoteServiceImpl implements StockQuoteService {

    @Autowired
    private StockQuoteRepository stockQuoteRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private StockQuoteMapper stockQuoteMapper;

    /**
     * Create the stock quote
     *
     * @param stockQuoteDTO The stock quote payload
     * @return the created stock quote otherwise return none.
     */
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

    /**
     * Find the stock quote by unique id
     *
     * @param id The stock quote unique id
     * @return the stock quote found otherwise return none.
     */
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

    /**
     * Get a list of all Stock quotes
     *
     * @return A list of all stock quote.
     */
    @Override
    public List<StockQuoteDTO> findAll() {
        return populateQuotes(stockQuoteMapper.entityToDtoStockList(stockQuoteRepository.findAll()));
    }


    /**
     * Find the stock quote by the stock id
     *
     * @param id The stock quote stock id
     * @return A list with all stock quote that refer to the stock id requested
     */
    @Override
    public List<StockQuoteDTO> findByStockId(String id) {
        return populateQuotes(stockQuoteMapper.entityToDtoStockList(stockQuoteRepository.findAllByStockId(id)));
    }


    /**
     * Convert a Map of quotes values from the payload to a List
     *
     * @param quotesMap The Map with all the quotes
     * @param idStock The stock id that will be assign to the quotes
     * @return A list with all quotes received in the payload
     */
    private List<QuotesDTO> extractQuotes(Map<String,String> quotesMap, String idStock){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        return quotesMap.entrySet().stream().map(m -> {
            QuotesDTO quotesDTO = new QuotesDTO();
            Date date = null;
            try {
                date = format.parse(m.getKey());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            quotesDTO.setIdStock(idStock);
            quotesDTO.setQuoteDate(date);
            quotesDTO.setQuoteValue(new BigDecimal(m.getValue().toString()));
            return quotesDTO;
        }).collect(Collectors.toList());
    }

    /**
     * Populate de Stock Quote quote list from quote database matching stock quote id
     *
     * @param stockQuoteDTOList The Stock Quote list to be populate with the quotes
     * @return A list with the Stock Quote populate with the proper quotes.
     */
    private List<StockQuoteDTO> populateQuotes(List<StockQuoteDTO> stockQuoteDTOList) {
        if(!stockQuoteDTOList.isEmpty()){
            stockQuoteDTOList.forEach(s -> {
                Map<String, String> quotes = new HashMap<>();
                List<QuotesDTO> quotesDTOList = stockQuoteMapper.entityToDtoQuotes(quoteRepository.findAllByIdStock(s.getId()));
                quotesDTOList.forEach(q -> {
                    quotes.put(q.getQuoteDate().toString(), q.getQuoteValue().toString());
                });
                s.setQuotes(quotes);
            });
        }
        return stockQuoteDTOList;
    }

}
