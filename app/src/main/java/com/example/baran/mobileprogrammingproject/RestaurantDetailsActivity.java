package com.example.baran.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RestaurantDetailsActivity extends AppCompatActivity {

    ImageView restaurantimage;
    TextView restaurantname,restaurantaddress;
    static ArrayList<Comments> commentText=new ArrayList<>();
    private static FirebaseFirestore db;
    static  RestaurantDetailsCommentAdapter adapter;
    Button do_comment;
    EditText user_comment;
    FirebaseAuth firebaseAuth;
    static Restaurants restaurants;
    Calendar calendar;
    String currentDate;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        restaurantname=findViewById(R.id.restaurantname);
        restaurantaddress=findViewById(R.id.restaurantaddress);
        restaurantimage=findViewById(R.id.restaurantimage);
        user_comment=findViewById(R.id.user_comment);
        do_comment=findViewById(R.id.do_comment);
        listView=findViewById(R.id.comment_list);
        calendar=Calendar.getInstance();
        currentDate=DateFormat.getInstance().format(calendar.getTime());
        adapter=new RestaurantDetailsCommentAdapter(this,R.layout.activity_comment_list,commentText);
        listView.setAdapter(adapter);
        restaurants=(Restaurants) getIntent().getSerializableExtra("restaurantdata");
        restaurantname.setText(restaurants.getRestaurantName());
        restaurantaddress.setText(restaurants.getRestaurantAddress());
        Glide.with(getApplicationContext()).asBitmap().load(restaurants.getRestaurantImage()).into(restaurantimage);
         db = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        getComments();
        do_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CollectionReference dbComments=db.collection("Yorumlar");
                Comments comments=new Comments(user_comment.getText().toString(),firebaseAuth.getUid(),currentDate,restaurants.getId(),calendar.getTime().toString());
                dbComments.add(comments).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(),"Yorum başarılıdır.",Toast.LENGTH_LONG).show();
                        getComments();
                        listView.setAdapter(adapter);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Yorum sırasında bir hata oluştu.",Toast.LENGTH_LONG).show();


                    }
                });

            }
        });
    }

    public static void getComments()
    {
        db.collection("Yorumlar").whereEqualTo("restoranid",restaurants.getId()).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        commentText.clear();
                        Comments comments;
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                comments=new Comments(d.getData().get("commentcontent").toString(),d.getData().get("userid").toString(),"",restaurants.getId(),d.getData().get("commenttime").toString());
                                comments.setId(d.getId());
                                commentText.add(comments);
                            }
                            adapter.notifyDataSetChanged();

                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()!=null)
        {
          do_comment.setVisibility(View.VISIBLE);
          user_comment.setVisibility(View.VISIBLE);
        }
        else
        {
            do_comment.setVisibility(View.INVISIBLE);
            user_comment.setVisibility(View.INVISIBLE);

        }
    }
}
