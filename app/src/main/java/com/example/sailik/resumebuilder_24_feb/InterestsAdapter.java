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

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.InterestsViewHolder> {

    public String email;
    private ArrayList<Interests> interests;

    private Context c;
    private DbHelper dbObj;


    public InterestsAdapter(Context c, ArrayList<Interests> interests,String email){
        this.c =c;
        this.interests = interests;
        dbObj= new DbHelper(c);
        interests = dbObj.getAllInterests(email);
        this.email=email;

    }
    int i=-1;
    @Override
    public InterestsAdapter.InterestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        for(int i=0;i<interests.size();i++) {
            View view;

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interestslist_data, parent, false);

            return new InterestsAdapter.InterestsViewHolder(view,i,interests);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(InterestsAdapter.InterestsViewHolder holder, int position) {

        holder.mInterestTV.setText(interests.get(position).getInterestText());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "#" + position + " - " + position, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(c,EditInterest.class);
                    i.putExtra("position",""+position);
                    i.putExtra("email",email);
                    c.startActivity(i);
                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return interests.size();
    }

    public void add(ArrayList<Interests> i){
        interests = i;
        Log.d("interests adapter add"," in add method");
        notifyItemRangeChanged(0,getItemCount());
        notifyDataSetChanged();
    }



    public static class InterestsViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mInterestTV;

        public void initializeElements() {
            mInterestTV = (TextView) itemView.findViewById(R.id.interest_tv);

        }

        private ItemClickListener clickListener;
        public InterestsViewHolder(View itemView, int i,ArrayList<Interests> list) {
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
