package com.example.baran.mobileprogrammingproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;

public class CommentUpdateActivity extends Activity {

    EditText editCommentText;
    Button updateButton;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_update);
        editCommentText=findViewById(R.id.commenttext);
        updateButton=findViewById(R.id.comment_update);
        final String commenttext=(String) getIntent().getSerializableExtra("commenttext");
        final String commentid=(String) getIntent().getSerializableExtra("commentid");
        editCommentText.setText(commenttext);
        db = FirebaseFirestore.getInstance();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Yorumlar").document(commentid).update("commentcontent",editCommentText.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(),"Guncellendi.",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
