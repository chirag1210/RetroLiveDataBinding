package com.chirag.retrofitdemo.service;

import com.chirag.retrofitdemo.model.Info;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetCountryDataService {

    @GET("country/get/all")
    Call<Info> getCountryList();

    @GET("country/get/iso2code/{name}")
    Call<Info> getCountryList(@Path("name") String countryCode);
}
