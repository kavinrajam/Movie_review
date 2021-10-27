package com.example.review;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ImageModel> courseModalArrayList;
    private Context context;

    // constructor
    public CourseRVAdapter(ArrayList<ImageModel> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ImageModel modal = courseModalArrayList.get(position);
        holder.Mname.setText("Movie Name: "+modal.getName());
        holder.dname.setText("Director: "+modal.getDirname());
        holder.actor.setText("Actors: "+modal.getActors());
        holder.rate.setText("Rating: "+String.valueOf(modal.getRate()));
        holder.IVPreviewImage.setImageBitmap(modal.getB());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView Mname,dname,actor,rate;
        //private float rate;
        Bitmap b;
        ImageView IVPreviewImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            Mname = itemView.findViewById(R.id.Mname);
            dname = itemView.findViewById(R.id.dirname);
            actor = itemView.findViewById(R.id.actors);
            rate = itemView.findViewById(R.id.rat);
            IVPreviewImage=itemView.findViewById(R.id.Poster);
        }
    }
}
