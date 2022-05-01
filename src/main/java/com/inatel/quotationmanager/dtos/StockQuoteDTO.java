package com.inatel.quotationmanager.dtos;

import java.util.Map;

public class StockQuoteDTO {

    private String id;
    private String stockId;
    private Map<String, String> quotes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }
}
