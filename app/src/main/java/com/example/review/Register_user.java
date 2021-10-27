package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register_user extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        e1=findViewById(R.id.nameU);
        e2=findViewById(R.id.emailU);
        e3=findViewById(R.id.passwordU);
        e4=findViewById(R.id.mobile);
        e5=findViewById(R.id.age);
    }
    public void onClickR(View view)
    {
        startActivity(new Intent(this,user_login.class));
    }
    public void onClickRE(View view)
    {
        DBManager d=new DBManager(this);
        int res=d.addRecord(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),e4.getText().toString(),e5.getText().toString());
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        startActivity(new Intent(this,user_login.class));
    }
}