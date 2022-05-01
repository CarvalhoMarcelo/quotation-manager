package com.inatel.quotationmanager.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Quotes")
@Table(name="QUOTES")
public class QuotesEntity {

    @Id
    private String id;

    private Date quote_date;
    private BigDecimal quote_value;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getQuote_date() {
        return quote_date;
    }

    public void setQuote_date(Date quote_date) {
        this.quote_date = quote_date;
    }

    public BigDecimal getQuote_value() {
        return quote_value;
    }

    public void setQuote_value(BigDecimal quote_value) {
        this.quote_value = quote_value;
    }
}
