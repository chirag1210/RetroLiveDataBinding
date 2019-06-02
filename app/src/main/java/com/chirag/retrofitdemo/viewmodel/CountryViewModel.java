package com.chirag.retrofitdemo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chirag.retrofitdemo.model.Result;
import com.chirag.retrofitdemo.repo.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository countryRepository;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
    }

    public LiveData<List<Result>> getCountries() {
        return countryRepository.getCountries();
    }
}
