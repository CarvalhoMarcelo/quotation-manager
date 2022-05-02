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
public class StockQuoteController {

    @Autowired
    private StockQuoteService stockQuoteService;

    @Autowired
    private StockManagerService stockManagerService;

    /**
     * Create a Stock Quote
     *
     * @param stockQuoteDTO Stock Quote payload
     * @return the Stock Quote created
     */
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createstockquote(@RequestBody StockQuoteDTO stockQuoteDTO) {

        if(stockQuoteDTO.getStockId() != null){
            List<LinkedHashMap<String,String>> stockManagerDTOList = stockManagerService.getStockManagerList();
            Boolean hasStockManager = stockManagerService.hasStockManager(stockManagerDTOList, stockQuoteDTO.getStockId());
            if(hasStockManager && !stockQuoteService.findById(stockQuoteDTO.getId()).isPresent()){
                Optional<StockQuoteDTO> stockQuote = stockQuoteService.create(stockQuoteDTO);
                return new ResponseEntity(stockQuoteDTO.getId(), HttpStatus.CREATED);
            }
        }
        return new ResponseEntity("Error!", HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Find a Stock Quote by Stock ID
     *
     * @param stockId code
     * @return a list of Stock Quotes regarding the stock id parameter
     */
    @RequestMapping(value = "/findbystockid/{stockId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StockQuoteDTO> findByStockId(@PathVariable("stockId") String stockId) {
        return stockQuoteService.findByStockId(stockId);
    }


    /**
     * List all Stock Quote
     *
     * @return a list of Stock Quotes
     */
    @RequestMapping(value = "/listallstockquote",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StockQuoteDTO> listall() {
        return stockQuoteService.findAll();
    }

    /**
     * Clear Stock Manager cache list
     *
     * @return Http status
     */
    @RequestMapping(value = "/stockcache",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> delstockchache() {
        stockManagerService.getNewStockManagerList();
        return new ResponseEntity("Cache empty", HttpStatus.OK);
    }


}
