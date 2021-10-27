package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

public class Select_movie extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btnAdd;
    String label,str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_movie);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        Intent intent=getIntent();
        str1=intent.getStringExtra("mail");
        loadSpinnerData();
    }
    public void loadSpinnerData() {
        DBManager db = new DBManager(this);
        List<String> labels = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        label = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void onClickGet(View view)
    {
        String str=label;
        Intent i = new Intent(getApplicationContext(), View_Review.class);
        i.putExtra("message_key", str);
        i.putExtra("mail",str1);
        startActivity(i);
    }
    public void onClick(View view)
    {
        String str=label;
        Intent intent = new Intent(getApplicationContext(), Give_Review.class);
        intent.putExtra("message_key", str);
        intent.putExtra("mail",str1);
        startActivity(intent);
    }
    public void onClickV(View view)
    {
        startActivity(new Intent(this,Status.class));
    }


}
