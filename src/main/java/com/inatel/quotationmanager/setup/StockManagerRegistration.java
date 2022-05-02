package com.inatel.quotationmanager.setup;

import com.inatel.quotationmanager.services.StockManagerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockManagerRegistration implements InitializingBean {

    @Autowired
    private StockManagerService stockManagerService;

    @Override
    public void afterPropertiesSet() throws Exception {
        stockManagerService.register();
    }
}
