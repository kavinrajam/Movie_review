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

import java.util.ArrayList;

public class Give_Review extends AppCompatActivity {
    String str,str1;
    TextView e1,e2,e3,e4,e5;
    RatingBar r1;
    EditText e6;
    ImageModel li;
    Bitmap bitmap;
    DBManager db;
    ImageView IVPreviewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give__review);
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
        e1.setText("Movie Name: "+li.getName());
        e2.setText("Director: "+li.getDirname());
        e3.setText("Actors:"+li.getActors());
        e4.setText("Rating: "+String.valueOf(li.getRate()));
        e5.setText("Description: "+li.getDesc());
        IVPreviewImage=findViewById(R.id.Poster);
        IVPreviewImage.setImageBitmap(li.getB());
        r1=(RatingBar)findViewById(R.id.ratingBar2);
        e6=findViewById(R.id.fed);
    }
    public void onClick(View view)
    {
        float k=(r1.getRating()+li.getRate())/2;
        db.addFeed(str1,str,e6.getText().toString());
        db.updateRate(str,k);
        startActivity(new Intent(this,Select_movie.class));

    }
}