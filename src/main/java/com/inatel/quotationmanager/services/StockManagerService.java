package com.inatel.quotationmanager.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;

import java.net.URISyntaxException;
import java.util.LinkedHashMap;
import java.util.List;

public interface StockManagerService {

    public List<LinkedHashMap<String, String>> getStockManagerList();

    public List<LinkedHashMap<String, String>> getNewStockManagerList();

    public Boolean hasStockManager(List<LinkedHashMap<String, String>> stockManagerDTOList, String stockId);

    public void register() throws JSONException, URISyntaxException, JsonProcessingException;

}
