package com.inatel.quotationmanager.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "StockQuote")
@Table(name="stock_quote")
public class StockQuoteEntity {

    @Id
    private String id;

    private String stockId;

    @OneToMany(mappedBy = "stockQuoteEntity", cascade = CascadeType.ALL)
    private List<QuotesEntity> quotes = new ArrayList<>();

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

    public List<QuotesEntity> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<QuotesEntity> quotes) {
        this.quotes = quotes;
    }
}
