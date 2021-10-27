package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class admin_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
    }
    public void onClickM(View view)
    {
        startActivity(new Intent(this,Movie_upload.class));
    }
    public void onClickL(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
    public void onClickS(View view) { startActivity(new Intent(this,Status.class));}
}