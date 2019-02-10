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

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder>  {
    private Context mCtx;
    private List<Hotels> hotelsList;

    public HotelsAdapter(Context mCtx, List<Hotels> hotelsList)
    {
        this.mCtx = mCtx;
        this.hotelsList = hotelsList;
    }
    @NonNull
    @Override
    public HotelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotelsViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_hotellist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HotelsAdapter.HotelsViewHolder holder, int position) {
        Hotels hotels = hotelsList.get(position);
        Glide.with(mCtx).asBitmap().load(hotels.getHotelImage()).into(holder.circleimageview_HotelImage);
        holder.HotelName.setText(hotels.getHotelName().toString());
    }

    @Override
    public int getItemCount() {
        return hotelsList.size();
    }

    public class HotelsViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView HotelName, HotelAddress;
        ImageView circleimageview_HotelImage;
        public HotelsViewHolder(View itemView) {
            super(itemView);
            HotelName = itemView.findViewById(R.id.textview_hotelName);
            circleimageview_HotelImage = itemView.findViewById(R.id.circleimageview_hotelImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Hotels hoteldata=hotelsList.get(getAdapterPosition());
            Intent intent=new Intent(mCtx,HotelDetailsActivity.class);
            intent.putExtra("hoteldata",hoteldata);
            mCtx.startActivity(intent);
        }
    }


}
