package com.example.baran.mobileprogrammingproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HotelsFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private HotelsAdapter adapter;
    private List<Hotels> hotelsList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_hotels,container,false);
        progressBar = view.findViewById(R.id.progressbarhotel);
        recyclerView = view.findViewById(R.id.recyclerview_hotels);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hotelsList = new ArrayList<>();
        adapter = new HotelsAdapter(getContext(), hotelsList);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Otel").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Hotels u = new Hotels(d.getId().toString(),d.getData().get("HotelName").toString(),d.getData().get("HotelImage").toString(),d.getData().get("HotelAddress").toString());
                                hotelsList.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return  view;

    }
}
