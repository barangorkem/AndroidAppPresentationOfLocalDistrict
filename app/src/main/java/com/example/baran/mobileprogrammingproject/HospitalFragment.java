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

public class HospitalFragment extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private HospitalAdapter adapter;
    private List<Hospitals> hospitalList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_hospital,container,false);
        progressBar = view.findViewById(R.id.progressbarhospitals);
        recyclerView = view.findViewById(R.id.recyclerview_hospitals);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        hospitalList = new ArrayList<>();
        adapter = new HospitalAdapter(getContext(), hospitalList);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Hastane").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Hospitals u = new Hospitals(d.getId().toString(),d.getData().get("HospitalName").toString(),d.getData().get("HospitalImage").toString(),d.getData().get("HospitalAddress").toString());
                                hospitalList.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return  view;

    }
}
