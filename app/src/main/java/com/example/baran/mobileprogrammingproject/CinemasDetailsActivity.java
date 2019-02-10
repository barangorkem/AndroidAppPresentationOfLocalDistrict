package com.example.baran.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CinemasDetailsActivity extends AppCompatActivity {

    ImageView cinemaimage;
    TextView cinemaname,cinemaaddress;
    private static FirebaseFirestore db;
    static Cinemas cinema;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinemas_details);
        cinemaname=findViewById(R.id.pharmacyname);
        cinemaaddress=findViewById(R.id.pharmacyaddress);
        cinemaimage=findViewById(R.id.pharmacyimage);
        cinema=(Cinemas) getIntent().getSerializableExtra("cinemadata");
        cinemaname.setText(cinema.getCinemaName());
        cinemaaddress.setText(cinema.getCinemaAddress());
        Glide.with(getApplicationContext()).asBitmap().load(cinema.getCinemaImage()).into(cinemaimage);
        db = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }
}
