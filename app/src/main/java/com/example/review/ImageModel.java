package com.example.review;

import android.graphics.Bitmap;

public class ImageModel {
    private String name,dirname,actors,desc;
    private float rate;
    private Bitmap b;
    public String getName()
    {
        return this.name;
    }
    public String getDirname()
    {
        return this.dirname;
    }
    public  String getActors()
    {
        return this.actors;
    }
    public float getRate()
    {
        return this.rate;
    }
    public Bitmap getB()
    {
        return this.b;
    }
    public String getDesc(){return this.desc;}
    public ImageModel()
    {

    }
    public ImageModel(String name,String dirName,String actors,float r,Bitmap bp)
    {
        this.name=name;
        this.dirname=dirName;
        this.actors=actors;
        this.rate=r;
        this.b=bp;
    }
    public void Image(String name,String dirName,String actors,float r,Bitmap bp,String desc)
    {
        this.name=name;
        this.dirname=dirName;
        this.actors=actors;
        this.rate=r;
        this.b=bp;
        this.desc=desc;
    }
}
