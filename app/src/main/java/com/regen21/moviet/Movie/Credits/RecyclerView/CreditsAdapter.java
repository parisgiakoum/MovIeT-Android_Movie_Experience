package com.regen21.moviet.Movie.Credits.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.regen21.moviet.Movie.Credits.CastModel;
import com.regen21.moviet.Movie.Credits.CrewModel;
import com.regen21.moviet.R;

import java.util.List;

public class CreditsAdapter extends RecyclerView.Adapter<CreditsViewHolder> {

    private List<CastModel> castData;
    private  List<CrewModel> crewData;
    CreditsMode mode;

    public CreditsAdapter(List<CastModel> castData, List<CrewModel> crewData, CreditsMode mode) {
        this.castData = castData;
        this.crewData = crewData;
        this.mode = mode;
    }

    @NonNull
    @Override
    public CreditsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_person_item,parent,false);
        return new CreditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditsViewHolder holder, int position) {
        if (mode == CreditsMode.CAST) {
            CastModel data = castData.get(position);
            holder.bind(data, null, CreditsMode.CAST);
        }
        else
        {
            CrewModel data = crewData.get(position)  ;
            holder.bind(null, data, CreditsMode.CREW);

        }

    }

    @Override
    public int getItemCount() {
        if (mode==CreditsMode.CAST) {
            return castData.size();
        }
        else {
            return crewData.size();
        }
    }
}
