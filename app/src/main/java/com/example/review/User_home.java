package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class User_home extends AppCompatActivity {
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Intent intent = getIntent();
        str = intent.getStringExtra("mail");
    }
    public void onClickL(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
    public void onClickM(View view)
    {
        Intent intent = new Intent(getApplicationContext(), Select_movie.class);
        intent.putExtra("mail", str);
        startActivity(intent);
    }
    public void onClickS(View view)
    {
        startActivity(new Intent(this,Status.class));
    }
}