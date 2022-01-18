package com.example.demo.proxy.impl;

import com.example.demo.proxy.MambuConnectionApi;
import com.example.demo.proxy.MambuConnectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MambuConnectionServiceImpl implements MambuConnectionService {

    private  MambuConnectionApi mambuConnectionApi;

    @Override
    public Object getMambuTransactionsByFilter(String startDate, String endDate, String type, Integer offset, Integer numberElements, String instance) throws IOException {
        return mambuConnectionApi.getMambuTransactionsByFilter(startDate, endDate,type, offset, numberElements, instance)
                .execute()
                .body();
    }

    @Override
    public Object getAllInstances() throws IOException {
        return mambuConnectionApi.getAllInstances().execute().body();
    }
}