package com.inatel.quotationmanager.services.impl;

import com.inatel.quotationmanager.services.StockManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StockManagerServiceImpl implements StockManagerService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<LinkedHashMap<String, String>> getStockManagerList() {

        try {
            List<LinkedHashMap<String, String>> stockManagerDTOList = new ArrayList<>();
            stockManagerDTOList = restTemplate.getForObject("http://localhost:8080/stock", List.class);
            return stockManagerDTOList;
        } catch (Exception e){
            Logger.getLogger(e.getMessage());
            return null;
        }

    }

    @Override
    public Boolean hasStockManager(List<LinkedHashMap<String, String>> stockManagerDTOList, String stockId){
        final String key = "id";
        for(LinkedHashMap<String, String> stockManager: stockManagerDTOList){
            if(stockManager.get(key).equalsIgnoreCase(stockId))
                return true;
        }
        return false;
    }

}
