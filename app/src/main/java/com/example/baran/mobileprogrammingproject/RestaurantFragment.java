package com.example.baran.mobileprogrammingproject;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class RestaurantFragment extends Fragment {

    private RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private List<Restaurants> restaurantList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=layoutInflater.inflate(R.layout.fragment_restaurant,container,false);
        progressBar = view.findViewById(R.id.progressbar);
        recyclerView = view.findViewById(R.id.recyclerview_restaurant);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        restaurantList = new ArrayList<>();
        adapter = new RestaurantAdapter(getContext(), restaurantList);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Restoran").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Restaurants u = new Restaurants(d.getId().toString(),d.getData().get("RestaurantName").toString(),d.getData().get("RestaurantImage").toString(),d.getData().get("RestaurantAddress").toString());
                                restaurantList.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return  view;
    }
}
