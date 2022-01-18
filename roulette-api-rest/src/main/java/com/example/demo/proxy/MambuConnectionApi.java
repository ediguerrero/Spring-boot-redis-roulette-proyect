package com.example.demo.proxy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface MambuConnectionApi {
//@Body to send objets in body no ()
    //@Query send objets in param
    @GET("account/transactions-filter")
    Call<Object> getMambuTransactionsByFilter(@Query("startDate") String startDate, @Query("endDate") String endDate, @Query("type") String type, @Query("offset") Integer offset, @Query("numberElements") Integer numberElements, @Header("instance")String instance);

    @GET("account/get-all-instances")
    Call<Object> getAllInstances();

}
