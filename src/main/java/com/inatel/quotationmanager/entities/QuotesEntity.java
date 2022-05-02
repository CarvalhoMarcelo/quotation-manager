package com.inatel.quotationmanager.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "Quotes")
@Table(name="QUOTES")
public class QuotesEntity {

    @Id
    @Column(nullable=false, unique=true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String idStock;
    private Date quoteDate;
    private BigDecimal quoteValue;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private StockQuoteEntity stockQuoteEntity;

    public QuotesEntity() {}


    public QuotesEntity(StockQuoteEntity stockQuoteEntity) {
        this.stockQuoteEntity = stockQuoteEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public StockQuoteEntity getStockQuoteEntity() {
        return stockQuoteEntity;
    }

    public void setStockQuoteEntity(StockQuoteEntity stockQuoteEntity) {
        this.stockQuoteEntity = stockQuoteEntity;
    }
}
