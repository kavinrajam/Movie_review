package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class View_Review extends AppCompatActivity {
    String str,str1;
    TextView e1,e2,e3,e4,e5,e6;
    ImageModel li;
    Bitmap bitmap;
    DBManager db;
    ImageView IVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__review);
        Intent intent = getIntent();
        str = intent.getStringExtra("message_key");
        str1=intent.getStringExtra("mail");
        db=new DBManager(this);
        li=db.readMovie(str);
        e1=findViewById(R.id.Mname);
        e2=findViewById(R.id.dirname);
        e3=findViewById(R.id.actors);
        e4=findViewById(R.id.rating);
        e5=findViewById(R.id.desc);
        e6=findViewById(R.id.fed);
        e1.setText("Movie Name: "+li.getName());
        e2.setText("Director: "+li.getDirname());
        e3.setText("Actors:"+li.getActors());
        e4.setText("Rating: "+String.valueOf(li.getRate()));
        e5.setText("Description: "+li.getDesc());
        //Toast.makeText(this,db.getFeed(str1,str),Toast.LENGTH_LONG).show();
        e6.setText("Feedback: "+db.getFeed(str1,str));
        IVPreviewImage=findViewById(R.id.Poster);
        IVPreviewImage.setImageBitmap(li.getB());
    }
    public void onClick(View view)
    {
        startActivity(new Intent(this,User_home.class));
    }
}