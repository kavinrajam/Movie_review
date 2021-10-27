package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        t1=(EditText)findViewById(R.id.emailid);
        t2=(EditText)findViewById(R.id.pass);
    }
    public void onClick(View view)
    {
        DBManager d=new DBManager(this);
        int res=d.checkRecord(t1.getText().toString(),t2.getText().toString());
        if(res==1)
            startActivity(new Intent(this,admin_home.class));
        else
            Toast.makeText(Admin.this,"Wrong Details",Toast.LENGTH_LONG).show();
        t1.setText("");
        t2.setText("");
    }
}