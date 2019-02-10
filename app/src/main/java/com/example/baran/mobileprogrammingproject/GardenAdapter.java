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

public class GardenAdapter extends RecyclerView.Adapter<GardenAdapter.GardenViewHolder> {
    private Context mCtx;
    private List<Gardens> gardensList;

    public GardenAdapter(Context mCtx, List<Gardens> gardensList)
    {
        this.mCtx = mCtx;
        this.gardensList = gardensList;
    }
    @NonNull
    @Override
    public GardenAdapter.GardenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GardenAdapter.GardenViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_gardenlist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull GardenAdapter.GardenViewHolder holder, int position) {
        Gardens gardens = gardensList.get(position);
        Glide.with(mCtx).asBitmap().load(gardens.getGardenImage()).into(holder.circleimageview_GardenImage);
        holder.GardenName.setText(gardens.getGardenName().toString());
    }

    @Override
    public int getItemCount() {
        return gardensList.size();
    }

    public class GardenViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView GardenName, GardenAddress;
        ImageView circleimageview_GardenImage;
        public GardenViewHolder(View itemView) {
            super(itemView);
            GardenName = itemView.findViewById(R.id.textview_gardenName);
            circleimageview_GardenImage = itemView.findViewById(R.id.circleimageview_gardenImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Gardens gardendata=gardensList.get(getAdapterPosition());
            Intent intent=new Intent(mCtx,GardenDetailsActivity.class);
            intent.putExtra("gardendata",gardendata);
            mCtx.startActivity(intent);
        }
    }

}
