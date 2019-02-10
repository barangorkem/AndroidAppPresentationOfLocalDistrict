package com.example.baran.mobileprogrammingproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button btn_signup;
    Button btn_login;
    TextView text;
    //Button btn_logout;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = layoutInflater.inflate(R.layout.fragment_home, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        //text=view.findViewById(R.id.textView);
        btn_signup=view.findViewById(R.id.btn_signup);
        btn_login=view.findViewById(R.id.btn_login);
       // btn_logout=view.findViewById(R.id.btn_logout);
        btn_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);
      //  btn_logout.setOnClickListener(this);
        Log.d("onCreate","onCreate");
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()!=null)
        {
            btn_signup.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.INVISIBLE);
           //btn_logout.setVisibility(View.VISIBLE);
        }
        else
        {
            btn_signup.setVisibility(View.VISIBLE);
            btn_login.setVisibility(View.VISIBLE);
           // btn_logout.setVisibility(View.INVISIBLE);
            //text.setText("Home Fragment");
        }
        Log.d("onStart","onStart");
    }


    /*
            @Override
            public void onResume() {

                super.onResume();
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    btn_login.setVisibility(View.INVISIBLE);
                    btn_signup.setVisibility(View.INVISIBLE);
                    btn_logout.setVisibility(View.VISIBLE);
                }
            }
        */
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {
            case R.id.btn_signup:
                intent=new Intent(getActivity(),SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            /*
            case R.id.btn_logout:
                firebaseAuth.signOut();
                btn_login.setVisibility(View.VISIBLE);
                btn_signup.setVisibility(View.VISIBLE);
                btn_logout.setVisibility(View.INVISIBLE);
               // text.setText("Home Fragment");
                break;
                */

            default:
                    break;
        }
    }
}

