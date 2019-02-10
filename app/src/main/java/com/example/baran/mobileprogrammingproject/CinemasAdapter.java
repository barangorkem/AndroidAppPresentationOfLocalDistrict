package com.example.baran.mobileprogrammingproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CinemasAdapter extends RecyclerView.Adapter<CinemasAdapter.CinemasViewHolder>  {
    private Context mCtx;
    private List<Cinemas> cinemasList;

    public CinemasAdapter(Context mCtx, List<Cinemas> cinemasList)
    {
        this.mCtx = mCtx;
        this.cinemasList = cinemasList;
    }
    @NonNull
    @Override
    public CinemasAdapter.CinemasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CinemasAdapter.CinemasViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_cinemalist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CinemasAdapter.CinemasViewHolder holder, int position) {
        Cinemas cinemas = cinemasList.get(position);
        Glide.with(mCtx).asBitmap().load(cinemas.getCinemaImage()).into(holder.circleimageview_CinemaImage);
        holder.CinemaName.setText(cinemas.getCinemaName().toString());
    }

    @Override
    public int getItemCount() {
        return cinemasList.size();
    }

    public class CinemasViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView CinemaName, CinemaAddress;
        ImageView circleimageview_CinemaImage;
        public CinemasViewHolder(View itemView) {
            super(itemView);
            CinemaName = itemView.findViewById(R.id.textview_cinemaName);
            circleimageview_CinemaImage = itemView.findViewById(R.id.circleimageview_cinemaImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Cinemas cinemadata=cinemasList.get(getAdapterPosition());
            Intent intent=new Intent(mCtx,CinemasDetailsActivity.class);
            intent.putExtra("cinemadata",cinemadata);
            mCtx.startActivity(intent);
        }
    }

}
