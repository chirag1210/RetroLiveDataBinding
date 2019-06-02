package com.chirag.retrofitdemo.repo;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.chirag.retrofitdemo.model.Info;
import com.chirag.retrofitdemo.model.Result;
import com.chirag.retrofitdemo.service.GetCountryDataService;
import com.chirag.retrofitdemo.service.RetrofitInstance;
import com.chirag.retrofitdemo.utils.Constants;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryRepository {

    private ArrayList<Result> results = new ArrayList<>();
    private MutableLiveData<List<Result>> mutableLiveData = new MutableLiveData<>();
    private Context context;

    public CountryRepository(Application application) {
        context = application.getBaseContext();
    }

    public MutableLiveData<List<Result>> getCountries() {
        GetCountryDataService getCountryDataService = RetrofitInstance.getService();
        Call<Info> call = getCountryDataService.getCountryList();
        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(@NonNull Call<Info> call, @NonNull Response<Info> response) {

                Info info = response.body();
                if (info == null) {
                    Toast.makeText(context,
                            "ERROR_CODE_RESPONSE_BODY_NULL" + Constants.StatusCodes.ERROR_CODE_RESPONSE_BODY_NULL,
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if (info.getRestResponse() != null) {
                    results = (ArrayList<Result>) info.getRestResponse().getResult();

                    for (Result result : results) {
                        Log.e("Result----->", result.getName());
                    }
                    mutableLiveData.postValue(results);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Info> call,@NonNull Throwable throwable) {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                if (throwable instanceof UnknownHostException) {
                    Toast.makeText(context,
                            "ERROR_CODE_UNKNOWN_HOST_EXCEPTION" + Constants.StatusCodes.ERROR_CODE_UNKNOWN_HOST_EXCEPTION,
                            Toast.LENGTH_LONG).show();
                } else if (throwable instanceof SocketTimeoutException) {
                    Toast.makeText(context,
                            "ERROR_CODE_SOCKET_TIMEOUT" + Constants.StatusCodes.ERROR_CODE_SOCKET_TIMEOUT,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context,
                            "ERROR_CODE_FAILURE_UNKNOWN" + Constants.StatusCodes.ERROR_CODE_FAILURE_UNKNOWN,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        return mutableLiveData;
    }
}
