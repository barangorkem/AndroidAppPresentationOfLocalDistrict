package com.example.baran.mobileprogrammingproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button LoginButton;
    EditText LoginEmail,LoginPassword;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        LoginEmail=findViewById(R.id.LoginEmail);
        LoginPassword=findViewById(R.id.LoginPassword);
        LoginButton=findViewById(R.id.LoginButton);
        firebaseAuth=FirebaseAuth.getInstance();
        LoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email,password;
        email=LoginEmail.getText().toString();
        password=LoginPassword.getText().toString();
        switch (v.getId())
        {
            case R.id.LoginButton:
                if (!email.isEmpty() && !password.isEmpty()) {
                    progressDialog.setMessage("Giriş Yapılıyor...");
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(LoginEmail.getText().toString(), LoginPassword.getText().toString()).
                            addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Başarılı", Toast.LENGTH_LONG).show();
                                        finish();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Böyle bir kullanıcı yoktur.", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Boş alan bıraktınız.",Toast.LENGTH_LONG).show();
                }
                break;
                default:break;
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
    }
}
