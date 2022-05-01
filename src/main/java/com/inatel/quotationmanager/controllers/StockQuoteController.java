package com.inatel.quotationmanager.controllers;

import com.inatel.quotationmanager.dtos.StockQuoteDTO;
import com.inatel.quotationmanager.services.StockManagerService;
import com.inatel.quotationmanager.services.StockQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stockquote")
public class StockQuoteController {

    @Autowired
    StockQuoteService stockQuoteService;

    @Autowired
    StockManagerService stockManagerService;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<StockQuoteDTO> create(@RequestBody StockQuoteDTO stockQuoteDTO) {

        if(stockQuoteDTO.getStockId() != null){
            List<LinkedHashMap<String,String>> stockManagerDTOList = stockManagerService.getStockManagerList();
            Boolean hasStockManager = stockManagerService.hasStockManager(stockManagerDTOList, stockQuoteDTO.getStockId());
            if(hasStockManager){
                Optional<StockQuoteDTO> stockQuote = stockQuoteService.create(stockQuoteDTO);
                return new ResponseEntity(stockQuoteDTO, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(stockQuoteDTO, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/findbystockid/{stockId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<StockQuoteDTO> findByStockId(@PathVariable("stockId") String stockId) {

        Optional<StockQuoteDTO> stockQuoteDTO = stockQuoteService.findByStockId(stockId);

        if(stockQuoteDTO.isPresent()) {
            return new ResponseEntity(stockQuoteDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Stock Quote id: " + stockId + " not found!", HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(value = "/listall",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<StockQuoteDTO>> listall() {

        List<StockQuoteDTO> stockQuoteDTOList = stockQuoteService.findAll();

        if(stockQuoteDTOList.isEmpty()) {
            return new ResponseEntity("No Stock Quotes found. List is empty!", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(stockQuoteDTOList, HttpStatus.OK);
        }

    }




}
