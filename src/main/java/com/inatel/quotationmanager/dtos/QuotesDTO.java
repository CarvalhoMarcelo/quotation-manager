package com.inatel.quotationmanager.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class QuotesDTO {

    private String id;

    private String idStock;
    private Date quoteDate;
    private BigDecimal quoteValue;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdStock() {
        return idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public BigDecimal getQuoteValue() {
        return quoteValue;
    }

    public void setQuoteValue(BigDecimal quoteValue) {
        this.quoteValue = quoteValue;
    }
}
