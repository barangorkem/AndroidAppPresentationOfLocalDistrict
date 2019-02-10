package com.example.baran.mobileprogrammingproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RestaurantDetailsCommentAdapter extends ArrayAdapter<Comments> {

    private  Context mContext;
    int mResource;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    static  int i=0;
    public RestaurantDetailsCommentAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Comments> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        firebaseAuth=FirebaseAuth.getInstance();
        String comment=getItem(position).getCommentcontent();
        String commenttime=getItem(position).getCommenttime();
        String id=getItem(position).getUserid();
        String isLogin=firebaseAuth.getUid();
        final String commentid=getItem(position).getId();
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView=layoutInflater.inflate(mResource,parent,false);
        TextView userscomment= convertView.findViewById(R.id.details_usercomment);
        final TextView userName=convertView.findViewById(R.id.details_username);
        db = FirebaseFirestore.getInstance();
        final Button sil = new Button(getContext());
        final Button guncelle=new Button(getContext());
        final LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.linear);

        db.collection("Kullanicilar").whereEqualTo("id",id).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        String username;
                        if(!queryDocumentSnapshots.isEmpty()){

                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for(DocumentSnapshot d : list){
                                username =d.getData().get("username").toString();
                                userName.setText(username);


                            }

                        }
                    }
                });
        if (firebaseAuth.getCurrentUser()!=null)
        {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams .WRAP_CONTENT);
            guncelle.setText("GUNCELLE");
            sil.setText("SİL");
            sil.setId(position);
            guncelle.setId(position);
            ll.addView(guncelle,lp);
            ll.addView(sil, lp);
            i++;
            if (id.trim().compareTo(isLogin.trim())==0)
            {
                sil.setVisibility(View.VISIBLE);
                guncelle.setVisibility(View.VISIBLE);
            }
            else
            {
                sil.setVisibility(View.INVISIBLE);
                guncelle.setVisibility(View.INVISIBLE);
            }
        }


        userscomment.setText(comment);

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                db.collection("Yorumlar").document(getItem(sil.getId()).getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getContext(),"Silindi",Toast.LENGTH_LONG).show();

                            notifyDataSetChanged();
                            RestaurantDetailsActivity.getComments();
                        }
                        else
                        {
                            Toast.makeText(getContext(),"Hata",Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });
        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Activity)mContext).finish();
                Intent intent=new Intent(getContext(),CommentUpdateActivity.class);
                intent.putExtra("commentid",getItem(guncelle.getId()).getId());
                intent.putExtra("commenttext",getItem(guncelle.getId()).getCommentcontent());
                mContext.startActivity(intent);
            }
        });

        return  convertView;
    }
}
