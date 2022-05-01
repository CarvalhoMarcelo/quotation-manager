package com.inatel.quotationmanager.mappers;

import com.inatel.quotationmanager.dtos.QuotesDTO;
import com.inatel.quotationmanager.dtos.StockQuoteDTO;
import com.inatel.quotationmanager.entities.QuotesEntity;
import com.inatel.quotationmanager.entities.StockQuoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface StockQuoteMapper {

    @Mapping(target = "quotes", ignore = true)
    StockQuoteDTO entityToDtoStock(StockQuoteEntity stockQuoteEntity);

    @Mapping(target = "quotes", ignore = true)
    List<StockQuoteDTO> entityToDtoStockList(List<StockQuoteEntity> stockQuoteEntity);

    @Mapping(target = "quotes", ignore = true)
    StockQuoteEntity dtoToEntityStock(StockQuoteDTO stockQuoteDTO);

    @Mapping(target = "quotes", ignore = true)
    List<StockQuoteEntity> dtoToEntityStockList(List<StockQuoteDTO> stockQuoteDTO);

    List<QuotesDTO> entityToDtoQuotes(List<QuotesEntity> stockQuoteEntityList);

    List<QuotesEntity> dtoToEntityQuotes(List<QuotesDTO> stockQuoteDTOList);

}
