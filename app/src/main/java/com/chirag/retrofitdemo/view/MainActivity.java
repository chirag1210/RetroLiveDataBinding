package com.chirag.retrofitdemo.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chirag.retrofitdemo.R;
import com.chirag.retrofitdemo.adapter.CountriesRecyclerViewAdapter;
import com.chirag.retrofitdemo.databinding.ActivityMainBinding;
import com.chirag.retrofitdemo.model.Result;
import com.chirag.retrofitdemo.viewmodel.CountryViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CountriesRecyclerViewAdapter.TopMoviesAdapterListener {

    private ArrayList<Result> results;
    private CountriesRecyclerViewAdapter adapter;
    private CountryViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        viewModel.getCountries();

        initAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        viewModel.getCountries().observe(this, results ->
                adapter.refreshRecyclerView(results));
    }

    private void initAdapter() {

        /*
         * 1. Init List
         * */
        results = new ArrayList<>();

        /*
         * 2. Init Adapter
         * */
        adapter = new CountriesRecyclerViewAdapter(results, this);

        RecyclerView recyclerView = binding.rvMovies;

        /*
         * Set Adapter to RecyclerView
         * */
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "" + results.get(position).getName(), Toast.LENGTH_LONG).show();
    }
}
