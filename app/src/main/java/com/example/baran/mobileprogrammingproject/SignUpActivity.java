package com.example.baran.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Username,Email,Password;
    Button UserSignUp;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        db=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        UserSignUp=findViewById(R.id.SignUp);
        Username=findViewById(R.id.Username);
        Email=findViewById(R.id.Email);
        Password= findViewById(R.id.Password);
        UserSignUp.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        String email,username,password;
        email=Password.getText().toString();
        username=Username.getText().toString();
        password=Password.getText().toString();
        switch (v.getId())
        {

            case R.id.SignUp:
                if (!email.isEmpty() && !username.isEmpty() || !password.isEmpty() ){
                    progressDialog.setMessage("Kayıt Ediliyor...");
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(Email.getText().toString(), Password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                CollectionReference dbKullanicilar = db.collection("Kullanicilar");
                                Users usersData = new Users(
                                        Username.getText().toString(),
                                        Password.getText().toString(),
                                        Email.getText().toString()
                                );
                                usersData.setId(firebaseAuth.getUid());
                                dbKullanicilar.add(usersData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Kayıt başarılıdır.", Toast.LENGTH_LONG).show();
                                        finish();
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Kayıt sırasında bir hata oluştu.", Toast.LENGTH_LONG).show();


                                    }
                                });

                            } else {
                                progressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "Bir hata oluştu.", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Boş alan bırakılmıştır.",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
