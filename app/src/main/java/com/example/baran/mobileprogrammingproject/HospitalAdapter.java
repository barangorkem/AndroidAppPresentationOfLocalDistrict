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

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    private Context mCtx;
    private List<Hospitals> hospitalList;

    public HospitalAdapter(Context mCtx, List<Hospitals> hospitalList)
    {
        this.mCtx = mCtx;
        this.hospitalList = hospitalList;
    }
    @NonNull
    @Override
    public HospitalAdapter.HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HospitalAdapter.HospitalViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_hospitallist, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.HospitalViewHolder holder, int position) {
        Hospitals hospitals = hospitalList.get(position);
        Glide.with(mCtx).asBitmap().load(hospitals.getHospitalImage()).into(holder.circleimageview_HospitalImage);
        holder.HospitalName.setText(hospitals.getHospitalName().toString());
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView HospitalName, HospitalAddress;
        ImageView circleimageview_HospitalImage;
        public HospitalViewHolder(View itemView) {
            super(itemView);
            HospitalName = itemView.findViewById(R.id.textview_hospitalName);
            circleimageview_HospitalImage = itemView.findViewById(R.id.circleimageview_hospitalmage);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Hospitals hospitaldata=hospitalList.get(getAdapterPosition());
            Intent intent=new Intent(mCtx,HospitalDetailsActivity.class);
            intent.putExtra("hospitaldata",hospitaldata);
            mCtx.startActivity(intent);
        }
    }

}
