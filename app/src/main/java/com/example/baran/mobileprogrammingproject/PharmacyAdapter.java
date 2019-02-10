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

public class PharmacyAdapter extends RecyclerView.Adapter<PharmacyAdapter.PharmacyViewHolder>  {

    private Context mCtx;
    private List<Pharmacy> pharmacyList;

    public PharmacyAdapter(Context mCtx, List<Pharmacy> pharmacyList)
    {
        this.mCtx = mCtx;
        this.pharmacyList = pharmacyList;
    }
    @NonNull
    @Override
    public PharmacyAdapter.PharmacyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PharmacyAdapter.PharmacyViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_pharmacylist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PharmacyAdapter.PharmacyViewHolder holder, int position) {
        Pharmacy pharmacy = pharmacyList.get(position);
        Glide.with(mCtx).asBitmap().load(pharmacy.getPharmacyImage()).into(holder.circleimageview_PharmacyImage);
        holder.PharmacyName.setText(pharmacy.getPharmacyName().toString());
    }

    @Override
    public int getItemCount() {
        return pharmacyList.size();
    }

    public class PharmacyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView PharmacyName, PharmacyAddress;
        ImageView circleimageview_PharmacyImage;
        public PharmacyViewHolder(View itemView) {
            super(itemView);
            PharmacyName = itemView.findViewById(R.id.textview_pharmacyName);
            circleimageview_PharmacyImage = itemView.findViewById(R.id.circleimageview_pharmacyImage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Pharmacy pharmacyData=pharmacyList.get(getAdapterPosition());
            Intent intent=new Intent(mCtx,PharmacyDetailsActvity.class);
            intent.putExtra("pharmacydata",pharmacyData);
            mCtx.startActivity(intent);
        }
    }
}
