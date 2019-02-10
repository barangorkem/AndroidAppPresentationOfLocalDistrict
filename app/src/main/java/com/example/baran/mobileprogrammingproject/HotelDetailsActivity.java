package com.example.baran.mobileprogrammingproject;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HotelDetailsActivity extends Activity {
    ImageView hotelimage;
    TextView hotelname,hoteladdress;
    private static FirebaseFirestore db;
    static Hotels hotels;
    Button do_comment;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        hotelname=findViewById(R.id.hotelname);
        hoteladdress=findViewById(R.id.hoteladress);
        hotelimage=findViewById(R.id.hotelimage);
        hotels=(Hotels) getIntent().getSerializableExtra("hoteldata");
        hotelname.setText(hotels.getHotelName());
        hoteladdress.setText(hotels.getHotelAddress());
        Glide.with(getApplicationContext()).asBitmap().load(hotels.getHotelImage()).into(hotelimage);
        db = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
    }

}
