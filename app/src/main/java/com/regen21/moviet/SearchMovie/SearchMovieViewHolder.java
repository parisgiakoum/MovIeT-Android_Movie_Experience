package com.regen21.moviet.SearchMovie;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.R;

//Layout of a single line
public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    public SearchMovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(String data) {
        TextView title = itemView.findViewById(R.id.holder_list_title);
        title.setText(data);

        TextView year = itemView.findViewById(R.id.holder_list_year);
        year.setText(data);

    }
}
