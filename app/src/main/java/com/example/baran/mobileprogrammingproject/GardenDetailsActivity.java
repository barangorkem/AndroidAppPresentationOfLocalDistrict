package com.example.baran.mobileprogrammingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class GardenDetailsActivity extends AppCompatActivity {

    ImageView gardenimage;
    TextView gardenname,gardenaddress;
    private static FirebaseFirestore db;
    static Gardens garden;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden_details);
        gardenname=findViewById(R.id.gardenname);
        gardenaddress=findViewById(R.id.gardenaddress);
        gardenimage=findViewById(R.id.gardenimage);
        garden=(Gardens) getIntent().getSerializableExtra("gardendata");
        gardenname.setText(garden.getGardenName());
        gardenaddress.setText(garden.getGardenAddress());
        Glide.with(getApplicationContext()).asBitmap().load(garden.getGardenImage()).into(gardenimage);
        db = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }
}
