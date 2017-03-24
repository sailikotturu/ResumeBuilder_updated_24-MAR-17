package com.example.sailik.resumebuilder_24_feb;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by saili.k on 21-03-2017.
 */

//public class ResumeAdapter {
//}
public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ProjectsViewHolder> {
//    private Context mContext;
//    private String[] mList;
//    public ResumeAdapter(Context contexts, String[] list) {
//        this.mContext = contexts;
//        this.mList = list;
//    }
    private List<TypeDecipher> mList;
    private ResumeHeader hObj;
    public String email;

    private Context c;
    private DbHelper dObj;
    public ResumeAdapter(Context c,List<TypeDecipher> list,String email,ResumeHeader hObj) {
        this.mList = list;
        this.email = email;
        this.hObj=hObj;
        this.c=c;
        dObj = new DbHelper(c);
    }
    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//
//        View itemView = inflater.inflate(R.layout.item, parent, false);
//        return new ViewHolder(itemView);
        View view;
        //switch (viewType) {
//            case 0:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_me, parent, false);
//                return new AboutMeViewHolder(view, email, hObj);

            //case 1:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_data, parent, false);

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_textview, parent, false);
                //view.setOnClickListener(this);
                return new ProjectsViewHolder(view);

//            case 2:
////                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_data, parent, false);
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_textview, parent, false);
//                return new EducationViewHolder(view, email, hObj);

//            case 3:
////                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interests_data, parent, false);
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interests_textview, parent, false);
//                return new InterestsViewHolder(view, email, hObj);

//            case 4:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_list_row, parent, false);
//                return new HeaderViewHolder(view, email, hObj);
        //}
        //return null;
    }

//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                if (isLongClick) {
//                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(c, "#" + position + " - " + position, Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(c,ProjectList.class);
//                    i.putExtra("email",email);
//                    c.startActivity(i);
//                }
//            }
//        });
//    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder holder, int position) {
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(c, "#" + position + " - " + position, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(c,ProjectList.class);
                    i.putExtra("email",email);
                    c.startActivity(i);
                }
            }
        });

    }
    @Override
    public int getItemViewType(int position) {
        if (mList != null) {
            TypeDecipher object = mList.get(position);
            if (object != null) {
                return object.getObjectType();
            }
        }
        return 0;
    }

    //    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        //holder.titleTextView.setText(mList[position]);
//        holder.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                if (isLongClick) {
//                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(mContext, "#" + position + " - " + mList[position], Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        TypeDecipher object = mList.get(position);
//        holder.setClickListener(new ItemClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                if (isLongClick) {
//                    //Toast.makeText(mContext, "#" + position + " - " + mList[position] + " (Long click)", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(mContext, "#" + position + " - " + mList[position], Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
    @Override
    public int getItemCount() {
        return mList.size();
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
        private Button mEdit;
        private Button mOk;
        private Button mAddBtn;
        private TextView mReadMore;
        DbHelper dbObj;
        Context c = itemView.getContext();
        String emailText;

        public ProjectsViewHolder(View itemView,String email,ResumeHeader hObj) {
            super(itemView);
            Log.d("adapter","projectholder");
            mProjectsTitle = (TextView) itemView.findViewById(R.id.projects_label_tv);
            mNameLabel = (TextView) itemView.findViewById(R.id.projectname_label_tv);
            mNameDesc = (TextView) itemView.findViewById(R.id.projectname_desc_tv);
            mDescLabel = (TextView) itemView.findViewById(R.id.projectdesc_label_tv);
            mDescDesc = (TextView) itemView.findViewById(R.id.projectdesc_tv);
            mDurationLabel = (TextView) itemView.findViewById(R.id.projectduration_label_tv);
            mDurationDesc = (TextView) itemView.findViewById(R.id.projectduration_tv);
            mNameEdit = (EditText) itemView.findViewById(R.id.projectname_desc_editText);
            mDescEdit = (EditText)itemView.findViewById(R.id.projectdesc_editText);
            mDurationEdit =(EditText)itemView.findViewById(R.id.projectduration_editText);
            mEdit = (Button) itemView.findViewById(R.id.project_edit_button);
            mOk = (Button) itemView.findViewById(R.id.project_ok_button);
            mAddBtn = (Button)itemView.findViewById(R.id.addproject_button);
            mReadMore = (TextView)itemView.findViewById(R.id.readmore_tv);

            mEdit.setOnClickListener(this);
            mOk.setOnClickListener(this);
            mReadMore.setOnClickListener(this);
            mAddBtn.setOnClickListener(this);
            dbObj = new DbHelper(c);
            if(dbObj.getProjectCount(email)==0){
                mEdit.setEnabled(false);
            }
            else {
                mEdit.setEnabled(true);


                Projects obj = dbObj.getRecentProject(email);
                mNameDesc.setText(obj.getProjectName());
                mDescDesc.setText(obj.getDescription());
                mDurationDesc.setText(obj.getProjectDuration());
            }
            emailText=email;

        }


        private TextView titleTextView;
        private ItemClickListener clickListener;
        public ProjectsViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.projects_textview);
            //itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            //itemView.setOnLongClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }



    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener{
        private TextView titleTextView;
        private ItemClickListener clickListener;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.textView);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }
        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}
