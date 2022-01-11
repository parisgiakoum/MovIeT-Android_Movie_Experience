package com.regen21.moviet.SearchMovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.R;

import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieViewHolder> {
    private List<String> arrayData;
    private Context context;

    public SearchMovieAdapter(List<String> arrayData, Context context){
        this.arrayData = arrayData;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.holder_movie_search_item, parent, false);
        return new SearchMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchMovieViewHolder holder, int position) {
        String data = arrayData.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return arrayData.size();
    }
}
