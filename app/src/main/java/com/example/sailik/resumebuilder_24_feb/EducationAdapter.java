package com.example.sailik.resumebuilder_24_feb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by saili.k on 22-03-2017.
 */

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.EducationViewHolder> {
    public String email;
    private ArrayList<Education> e;

    private Context c;


    public EducationAdapter(Context c, ArrayList<Education> e,String email){
        this.c =c;
        this.e = e;
        this.email=email;
        for(int i=0;i<e.size();i++) {
            Log.d("education list", "" + e.get(i).getCourseOfStudy());
        }
    }
    int i=-1;
    @Override
    public EducationAdapter.EducationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        for(int i=0;i<e.size();i++) {
            View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.educationlist_data, parent, false);

            return new EducationAdapter.EducationViewHolder(view,i,e);

        }

        return null;
    }
    //int j=-1;
    @Override
    public void onBindViewHolder(EducationAdapter.EducationViewHolder holder, int position) {
       // j++;
        holder.mCourse.setText(e.get(position).getCourseOfStudy());
        holder.mCollege.setText(e.get(position).getCollegeName());
        holder.mDuration.setText(e.get(position).getStudyDuration());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "#" + position + " - " + position, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(c,EditEducation.class);
                    i.putExtra("position",""+position);
                    i.putExtra("email",email);
                    c.startActivity(i);
                }
            }
        });

    }

    public void add(ArrayList<Education> e){
        this.e = e;
        //Log.d("interests adapter add"," in add method");
        notifyItemRangeChanged(0,getItemCount());
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return e.size();
    }



    public static class EducationViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mCourse;
        private TextView mCollege;
        private TextView mDuration;
        DbHelper dbObj;


        public void initializeElements() {
            mCourse = (TextView) itemView.findViewById(R.id.coursename_desc_tv);
            mCollege = (TextView) itemView.findViewById(R.id.collegename_desc_tv);
            mDuration = (TextView) itemView.findViewById(R.id.duration_desc_tv);

        }

        private ItemClickListener clickListener;

        public EducationViewHolder(View itemView, int i,ArrayList<Education> list) {
            super(itemView);
            initializeElements();

            itemView.setOnClickListener(this);

        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }



    }
}
