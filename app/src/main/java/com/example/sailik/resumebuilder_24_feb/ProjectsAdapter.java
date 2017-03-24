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
import java.util.List;

/**
 * Created by saili.k on 22-03-2017.
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    public String email;
    private ArrayList<Projects> p;
    private Context c;

    public ProjectsAdapter(Context c, ArrayList<Projects> p,String email){
        this.c =c;
        this.p = p;
        this.email=email;
        for(int i=0;i<p.size();i++) {
            Log.d("project list", "" + p.get(i).getProjectName());
        }
    }
    int i=-1;
    @Override
    public ProjectsAdapter.ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        for(int i=0;i<p.size();i++) {
            View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projectlist_data, parent, false);

            return new ProjectsAdapter.ProjectsViewHolder(view,i,p);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(ProjectsAdapter.ProjectsViewHolder holder, int position) {

        holder.mNameDesc.setText(p.get(position).getProjectName());
        holder.mDescDesc.setText(p.get(position).getDescription());
        holder.mDurationDesc.setText(p.get(position).getProjectDuration());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "#" + position + " - " + position, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(c,EditProject.class);
                    i.putExtra("position",""+position);
                    i.putExtra("email",email);
                    c.startActivity(i);
                }
            }
        });

    }

    public void add(ArrayList<Projects> p){
        this.p = p;
        //Log.d("interests adapter add"," in add method");
        notifyItemRangeChanged(0,getItemCount());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return p.size();
    }

    public static class ProjectsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mProjectsTitle;
        private TextView mNameLabel;
        private TextView mNameDesc;
        private TextView mDescLabel;
        private TextView mDescDesc;
        private TextView mDurationLabel;
        private TextView mDurationDesc;
        private EditText mNameEdit;
        private EditText mDescEdit;
        private EditText mDurationEdit;
        DbHelper dbObj;

        public void initializeElements() {
                mNameLabel = (TextView) itemView.findViewById(R.id.projectname_label_tv);
                mNameDesc = (TextView) itemView.findViewById(R.id.projectname_desc_tv);
                mDescLabel = (TextView) itemView.findViewById(R.id.projectdesc_label_tv);
                mDescDesc = (TextView) itemView.findViewById(R.id.projectdesc_tv);
                mDurationLabel = (TextView) itemView.findViewById(R.id.projectduration_label_tv);
                mDurationDesc = (TextView) itemView.findViewById(R.id.projectduration_tv);
        }


        private ItemClickListener clickListener;
        public ProjectsViewHolder(View itemView, int i,ArrayList<Projects> list) {
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
