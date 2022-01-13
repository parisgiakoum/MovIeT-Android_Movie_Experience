package com.regen21.moviet.SearchMovie;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.R;

//Layout of a single line
public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    private boolean clicked = false;

    public SearchMovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(String data) {
        TextView title = itemView.findViewById(R.id.holder_list_title);
        title.setText(data);

        TextView year = itemView.findViewById(R.id.holder_list_year);
        year.setText(data);

        Button addIcon = itemView.findViewById(R.id.holder_list_save_icon);
        addIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!clicked) {
                    clicked = true;
                    addIcon.setBackgroundResource(R.drawable.ic_baseline_check);
                    //addIcon.setText(R.string.str_added);
                }
                else {
                    clicked = false;
                    addIcon.setBackgroundResource(R.drawable.ic_baseline_library_add_24);
                    //addIcon.setText(R.string.app_name);
                }
            }
        });


    }
}
