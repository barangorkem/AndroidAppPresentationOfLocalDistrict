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

public class CinemaFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private CinemasAdapter adapter;
    private List<Cinemas> cinemaList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_cinema,container,false);
        progressBar = view.findViewById(R.id.progressbarcinema);
        recyclerView = view.findViewById(R.id.recyclerview_cinema);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cinemaList = new ArrayList<>();
        adapter = new CinemasAdapter(getContext(), cinemaList);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Sinema").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Cinemas u = new Cinemas(d.getId().toString(),d.getData().get("CinemaName").toString(),d.getData().get("CinemaImage").toString(),d.getData().get("CinemaAddress").toString());
                                cinemaList.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return  view;

    }
}
