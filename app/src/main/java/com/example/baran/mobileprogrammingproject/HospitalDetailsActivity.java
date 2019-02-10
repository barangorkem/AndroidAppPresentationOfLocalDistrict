package com.example.baran.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class HospitalDetailsActivity extends AppCompatActivity {

    ImageView hospitalimage;
    TextView hospitalname,hospitaladdress;
    private static FirebaseFirestore db;
    static Hospitals hospital;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_details);
        hospitalname=findViewById(R.id.hospitalname);
        hospitaladdress=findViewById(R.id.hospitaladdress);
        hospitalimage=findViewById(R.id.hospitalimage);
        hospital=(Hospitals) getIntent().getSerializableExtra("hospitaldata");
        hospitalname.setText(hospital.getHospitalName());
        hospitaladdress.setText(hospital.getHospitalAddress());
        Glide.with(getApplicationContext()).asBitmap().load(hospital.getHospitalImage()).into(hospitalimage);
        db = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }
}
