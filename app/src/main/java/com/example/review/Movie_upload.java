package com.example.review;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
public class Movie_upload extends AppCompatActivity {
    Button BSelectImage;
    ImageView IVPreviewImage;
    EditText e1,e2,e3,e4;
    Bitmap bitmap;
    RatingBar r1;
    float getrating;
    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_upload);
        BSelectImage = findViewById(R.id.BSelectImage);
        IVPreviewImage = findViewById(R.id.IVPreviewImage);
        e1=findViewById(R.id.namem);
        e2=findViewById(R.id.dire);
        e3=findViewById(R.id.actor);
        r1=(RatingBar)findViewById(R.id.ratingBar);
        e4=findViewById(R.id.fed);
        BSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });
    }
    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                IVPreviewImage.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
            }
        }
    }
    public void onClick(View view)
    {
        DBManager d=new DBManager(this);
        getrating = r1.getRating();
        d.insertImg(e1.getText().toString(),e2.getText().toString(),e3.getText().toString(),getrating,bitmap,e4.getText().toString());
        e1.setText("");
        e2.setText("");
        e3.setText("");
        r1.setRating(0);
        e4.setText("");
        IVPreviewImage.setImageBitmap(null);
        startActivity(new Intent(this,admin_home.class));
    }
}