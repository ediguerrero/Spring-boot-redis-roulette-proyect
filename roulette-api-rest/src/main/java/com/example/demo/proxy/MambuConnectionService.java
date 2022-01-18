package com.example.demo.proxy;

import java.io.IOException;

public interface MambuConnectionService {

    Object getMambuTransactionsByFilter(String startDate, String endDate, String type,
                                                   Integer offset, Integer numberElements, String instance) throws IOException;

    Object getAllInstances() throws IOException;
}
