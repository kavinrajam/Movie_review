package com.example.review;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class user_login extends AppCompatActivity {
    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        e1=findViewById(R.id.emaili);
        e2=findViewById(R.id.passwordU);
    }
    public void onClick(View view)
    {
        DBManager d=new DBManager(this);
        int res=d.confirm_login(e1.getText().toString(),e2.getText().toString());
        if(res==1) {
            Intent intent = new Intent(getApplicationContext(), User_home.class);
            intent.putExtra("mail", e1.getText().toString());
            startActivity(intent);
            //startActivity(new Intent(this, User_home.class));
        }
         else
            Toast.makeText(user_login.this,"Wrong Details",Toast.LENGTH_LONG).show();
        e1.setText("");
        e2.setText("");
    }
    public void OnclickR(View view)
    {
        startActivity(new Intent(this,Register_user.class));
    }
}