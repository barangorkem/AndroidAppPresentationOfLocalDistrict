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

public class PharmacyFragment extends Fragment implements View.OnClickListener {

    View view;
    private RecyclerView recyclerView;
    private PharmacyAdapter adapter;
    private List<Pharmacy> pharmacyList;
    private ProgressBar progressBar;
    private FirebaseFirestore db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = layoutInflater.inflate(R.layout.fragment_pharmacy, container, false);
        progressBar = view.findViewById(R.id.progressbarpharmacy);
        recyclerView = view.findViewById(R.id.recyclerview_pharmacy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pharmacyList = new ArrayList<>();
        adapter = new PharmacyAdapter(getContext(), pharmacyList);
        recyclerView.setAdapter(adapter);
        db = FirebaseFirestore.getInstance();
        db.collection("Eczane").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        progressBar.setVisibility(View.GONE);
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                Pharmacy u = new Pharmacy(d.getId().toString(),d.getData().get("PharmacyName").toString(),d.getData().get("PharmacyImage").toString(),d.getData().get("PharmacyAddress").toString());
                                pharmacyList.add(u);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        return  view;
    }

    @Override
    public void onClick(View v) {

    }
}
