package com.inatel.quotationmanager.services;

import com.inatel.quotationmanager.services.impl.StockManagerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class StockManagerServiceTest {

    @Mock
    private RestTemplate restTemplate = new RestTemplate();

    @InjectMocks
    private StockManagerServiceImpl stockManagerService = new StockManagerServiceImpl();

    private final static String stockManagerUrl = "http://localhost:8080";
    private final List<LinkedHashMap<String, String>> stockManagerList = new ArrayList<LinkedHashMap<String, String>>();

    @Before
    public void setup(){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<String,String>();
        linkedHashMap.put("id","petr4");
        linkedHashMap.put("description", "Petrobras PN");
        stockManagerList.add(linkedHashMap);
        linkedHashMap.put("id","vale5");
        linkedHashMap.put("description", "Vale do Rio Doce PN");
        stockManagerList.add(linkedHashMap);
    }


    @Test
    public void whenReturnedListIsNotEmpty_thenReturnTheList(){
        Mockito.when(restTemplate.getForObject(stockManagerUrl +  "/stock", List.class))
          .thenReturn(stockManagerList);

        List<LinkedHashMap<String, String>> map = stockManagerService.getStockManagerList();
        Assertions.assertEquals(stockManagerList, map);
    }


}


