package com.inatel.quotationmanager.services;

import java.util.LinkedHashMap;
import java.util.List;

public interface StockManagerService {

    public List<LinkedHashMap<String, String>> getStockManagerList();

    public Boolean hasStockManager(List<LinkedHashMap<String, String>> stockManagerDTOList, String stockId);

}
