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

public class GardenFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private GardenAdapter adapter;
    private List<Gardens> gardensList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_garden,container,false);
        progressBar = view.findViewById(R.id.progressbargardens);
        recyclerView = view.findViewById(R.id.recyclerview_gardens);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        gardensList = new ArrayList<>();
        adapter = new GardenAdapter(getContext(), gardensList);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Park").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Gardens u = new Gardens(d.getId().toString(),d.getData().get("GardenName").toString(),d.getData().get("GardenImage").toString(),d.getData().get("GardenAddress").toString());
                                gardensList.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return  view;

    }
}
