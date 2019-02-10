package com.example.baran.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PharmacyDetailsActvity extends AppCompatActivity {

    ImageView pharmacyimage;
    TextView pharmacyname,pharmacyaddress;
    private static FirebaseFirestore db;
    static Pharmacy pharmacy;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_details_actvity);
        pharmacyname=findViewById(R.id.pharmacyname);
        pharmacyaddress=findViewById(R.id.pharmacyaddress);
        pharmacyimage=findViewById(R.id.pharmacyimage);
        pharmacy=(Pharmacy) getIntent().getSerializableExtra("pharmacydata");
        pharmacyname.setText(pharmacy.getPharmacyName());
        pharmacyaddress.setText(pharmacy.getPharmacyAddress());
        Glide.with(getApplicationContext()).asBitmap().load(pharmacy.getPharmacyImage()).into(pharmacyimage);
        db = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }
}
