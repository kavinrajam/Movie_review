package com.example.review;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final String dbname = "MovieReview.db";

    public DBManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table admin (id integer primary key autoincrement,email varchar(50),pass varchar(50));";
        db.execSQL(qry);
        ContentValues cv = new ContentValues();
        cv.put("email", "rkavin78@gmail.com");
        cv.put("pass", "pass");
        db.insert("admin", null, cv);
        String qry1 = "create table image_details (id integer primary key autoincrement,name varchar(50),dirname varchar(50),actors varchar(100),rate float,image BLOB,descp varchar(100));";
        db.execSQL(qry1);
        String q1 = "create table register(id integer primary key autoincrement,name varchar(50),email varchar(50),pass varchar(50),phone varchar(10),age varchar(2));";
        db.execSQL(q1);
        String q2 = "create table feedback(email varchar(50),name varchar(50),feed varchar(200));";
        db.execSQL(q2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS admin");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS image_details");
        onCreate(db);
        db.execSQL("drop table if exists register");
        onCreate(db);
        db.execSQL("drop table if exists feedback");
        onCreate(db);
    }

    public int addRecord(String p1, String p2, String p3, String p4, String p5) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", p1);
        cv.put("email", p2);
        cv.put("pass", p3);
        cv.put("phone", p4);
        cv.put("age", p5);
        long res = db.insert("register", null, cv);
        if (res == -1)
            return 1;
        else
            return -1;
    }

    public int addFeed(String p1, String p2, String p3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", p1);
        cv.put("name", p2);
        cv.put("feed", p3);
        long res = db.insert("feedback", null, cv);
        if (res == -1)
            return -1;
        else
            return 1;
    }

    public void updateRate(String name, float p1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rate", p1);
        db.update("image_details", values, "name=?", new String[]{name});
        //db.execSQL("UPDATE image_details SET rate="+p1+"WHERE name="+name);
    }

    public int confirm_login(String p1, String p2) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("Select id from register where email=? and pass=?", new String[]{p1, p2});
        if (mCursor.getCount() > 0)
            return 1;
        else
            return -1;
    }

    public int checkRecord(String p1, String p2) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery("Select id from admin where email =? and pass=?", new String[]{p1, p2});
        if (mCursor.getCount() > 0)
            return 1;
        else
            return -1;
    }

    public void insertImg(String p1, String p2, String p3, float p4, Bitmap img, String p5) {


        byte[] data = getBitmapAsByteArray(img); // this is a function
        ContentValues cv = new ContentValues();
        //cv.put(KEY_NAME,    name);
        SQLiteDatabase db = this.getWritableDatabase();
        cv.put("name", p1);
        cv.put("dirname", p2);
        cv.put("actors", p3);
        cv.put("rate", p4);
        cv.put("image", data);
        cv.put("descp", p5);
        db.insert("image_details", null, cv);

    }

    public Bitmap image_return(String p1) {
        SQLiteDatabase db = this.getWritableDatabase();
        String qu = "select image  from image_details where name=" + p1;
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cursor cur = db.rawQuery(qu, null);

        if (cur.moveToFirst()) {
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null;
    }

    public ArrayList<ImageModel> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM image_details", null);

        // on below line we are creating a new array list.
        ArrayList<ImageModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                byte[] imgByte = cursorCourses.getBlob(5);
                Bitmap b = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
                courseModalArrayList.add(new ImageModel(cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getFloat(4), b));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    public ImageModel readMovie(String p1) {
        // on below line we are creating a
        // database for reading our database.
        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM image_details where name=?", new String[]{p1});

        // on below line we are creating a new array list.
        ImageModel m = new ImageModel();
        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {

            // on below line we are adding the data from cursor to our array list.
            byte[] imgByte = cursorCourses.getBlob(5);
            Bitmap b = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            m.Image(cursorCourses.getString(1),
                    cursorCourses.getString(2),
                    cursorCourses.getString(3),
                    cursorCourses.getFloat(4), b,
                    cursorCourses.getString(6));

            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return m;
    }

    public String getFeed(String p1, String p2) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from feedback where email=? and name=?", new String[]{p1, p2});
        String s = "";
        if (c.getCount() < 0) {
            return "Nothing";
        }
        if (c.moveToFirst()) {
            do {
                s = c.getString(2);
            } while (c.moveToNext());
        }
        return s;
    }


    public List<String> getAllLabels(){
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM image_details";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
