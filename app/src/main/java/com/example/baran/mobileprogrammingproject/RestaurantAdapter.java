package com.example.baran.mobileprogrammingproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RestaurantAdapter extends  RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>  {

    private Context mCtx;
    private List<Restaurants> restaurantList;

    public RestaurantAdapter(Context mCtx, List<Restaurants> restaurantList)
    {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_restaurantlist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurants restaurants = restaurantList.get(position);
        Glide.with(mCtx).asBitmap().load(restaurants.getRestaurantImage()).into(holder.circleimageview_restaurantImage);
        holder.textRestaurantName.setText(restaurants.getRestaurantName().toString());
        //holder.textRestaurantAddress.setText(restaurants.getRestaurantAddress().toString());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView textRestaurantName, textRestaurantAddress;
        ImageView circleimageview_restaurantImage;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            textRestaurantName = itemView.findViewById(R.id.textview_restaurantName);
            circleimageview_restaurantImage = itemView.findViewById(R.id.circleimageview_restaurantImage);
            //textRestaurantAddress = itemView.findViewById(R.id.textview_restaurantAddress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           Restaurants restaurantData=restaurantList.get(getAdapterPosition());
            Intent intent=new Intent(mCtx,RestaurantDetailsActivity.class);
            intent.putExtra("restaurantdata",restaurantData);
            mCtx.startActivity(intent);
        }
    }
}
