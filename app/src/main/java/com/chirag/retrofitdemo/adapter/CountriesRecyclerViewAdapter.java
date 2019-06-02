package com.chirag.retrofitdemo.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chirag.retrofitdemo.R;
import com.chirag.retrofitdemo.databinding.CountryItemRowBinding;
import com.chirag.retrofitdemo.model.Result;

import java.util.ArrayList;
import java.util.List;

public class CountriesRecyclerViewAdapter extends RecyclerView.Adapter<CountriesRecyclerViewAdapter.CountriesViewHolder> {
    /*
     * Global Instance of Interface
     * */
    private TopMoviesAdapterListener listener;

    /*
     * Global Instance of List to store all the movies
     * */
    private List<Result> topMoviesModelList;

    private LayoutInflater layoutInflater;

    /*
     * Constructor
     * */
    public CountriesRecyclerViewAdapter(ArrayList<Result> topMoviesModelList, TopMoviesAdapterListener listener) {
        this.topMoviesModelList = topMoviesModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CountryItemRowBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.country_item_row, parent, false);
        return new CountriesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        Result topMoviesModel = topMoviesModelList.get(position);
        holder.binding.setResult(topMoviesModel);

        holder.itemView.setOnClickListener(v -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return topMoviesModelList.size();
    }

    public void refreshRecyclerView(List<Result> newMoviesList) {
        if (this.topMoviesModelList != null) {
            this.topMoviesModelList.clear();
            this.topMoviesModelList.addAll(newMoviesList);
        } else {
            this.topMoviesModelList = newMoviesList;
        }

        notifyDataSetChanged();
    }

    /*
     * Interface for Callbacks to MainActivity
     * */
    public interface TopMoviesAdapterListener {
        void onItemClick(int position);
    }

    /*
     * ViewHolder
     * */
    class CountriesViewHolder extends RecyclerView.ViewHolder {

        private final CountryItemRowBinding binding;

        CountriesViewHolder(CountryItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.binding = itemRowBinding;
        }
    }
}