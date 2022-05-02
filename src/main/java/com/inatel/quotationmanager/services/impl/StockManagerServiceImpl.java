package com.inatel.quotationmanager.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.inatel.quotationmanager.services.StockManagerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

@Service
public class StockManagerServiceImpl implements StockManagerService {

    @Autowired
    private RestTemplate restTemplate;

    private final static List<LinkedHashMap<String, String>> stockManagerDTOList = new ArrayList<>();
    private final static String stockManagerUrl = "http://localhost:8080";
    private final static Integer serverPort = 8081;
    private final static String serverAddress = "localhost";

    /**
     * Get Stock Manager List from external service and cache it
     *
     * @return a List of Linked Hash Map returned by the service
     */
    @Override
    @Cacheable("stockmanager")
    public List<LinkedHashMap<String, String>> getStockManagerList() {
        return getList();
    }

    /**
     * Clear Stock Manager cache List
     *
     * @return a empty List of Linked Hash Map
     */
    @Override
    @CacheEvict(value = "stockmanager", allEntries = true)
    public List<LinkedHashMap<String, String>> getNewStockManagerList() {
        return getList();
    }

    /**
     * Check if the stock id exist in Stock Manager service
     *
     * @param stockManagerDTOList A Linked Hash Map with the List of allowed stock id
     * @param stockId The stock id to be checked against the Stock Manager list
     * @return a empty List of Linked Hash Map
     */
    @Override
    public Boolean hasStockManager(List<LinkedHashMap<String, String>> stockManagerDTOList, String stockId){
        final String key = "id";
        for(LinkedHashMap<String, String> stockManager: stockManagerDTOList){
            if(stockManager.get(key).equalsIgnoreCase(stockId))
                return true;
        }
        return false;
    }

    /**
     * Register the Stock Quote service at the start on the Stock Manager service
     *
     */
    @Override
    public void register() throws JSONException, URISyntaxException, JsonProcessingException {

        String registerUrl = stockManagerUrl + "/notification";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("host", serverAddress);
        personJsonObject.put("port", serverPort);

        HttpEntity<String> request = new HttpEntity<String>(personJsonObject.toString(), headers);

        restTemplate.postForObject(registerUrl, request, String.class);

    }

    /**
     * Get Stock Manager List from external service
     *
     * @return a List of Linked Hash Map returned by the service
     */
    private List<LinkedHashMap<String, String>> getList(){
        try {
            return restTemplate.getForObject(stockManagerUrl +  "/stock", List.class);
        } catch (Exception e){
            Logger.getLogger(e.getMessage());
            return null;
        }
    }

}

