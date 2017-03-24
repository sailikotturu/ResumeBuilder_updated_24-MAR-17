//package com.example.sailik.resumebuilder_24_feb;
//
//import android.content.Context;
//import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
//import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.target.BitmapImageViewTarget;
//
//import java.util.List;
//
///**
// * Created by saili.k on 03-03-2017.
// */
//
//
//public class DifferentRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    private List<TypeDecipher> mList;
//    private ResumeHeader hObj;
//    public String email;
//
//    private Context c;
//    private DbHelper dObj;
//    public DifferentRowAdapter(Context c,List<TypeDecipher> list,String email,ResumeHeader hObj) {
//        this.mList = list;
//        this.email = email;
//        this.hObj=hObj;
//        this.c=c;
//        dObj = new DbHelper(c);
//    }
//
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view;
//        switch (viewType) {
//            case 0:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_me, parent, false);
//                return new AboutMeViewHolder(view,email,hObj);
//
//            case 1:
////                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_data, parent, false);
//
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_textview, parent, false);
//                //view.setOnClickListener(this);
//                return new ProjectsViewHolder(view,email,hObj);
//
//            case 2:
////                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_data, parent, false);
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_textview, parent, false);
//                return new EducationViewHolder(view,email,hObj);
//
//            case 3:
////                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interests_data, parent, false);
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interests_textview, parent, false);
//                return new InterestsViewHolder(view,email,hObj);
//
//            case 4:
//                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_list_row, parent, false);
//                return new HeaderViewHolder(view,email,hObj);
//
//        }
//        return null;
//    }
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//    }
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        TypeDecipher object = mList.get(position);
//
//    }
//    @Override
//    public int getItemCount() {
//        if (mList == null)
//            return 0;
//        return mList.size();
//    }
//    @Override
//    public int getItemViewType(int position) {
//        if (mList != null) {
//            TypeDecipher object = mList.get(position);
//            if (object != null) {
//                return object.getObjectType();
//            }
//        }
//        return 0;
//    }
//
//    public void swap(){
////        data.clear();
////        data.addAll(datas);
//        notifyItemRangeChanged(0,mList.size());
//        notifyDataSetChanged();
//    }
//
////    @Override
////    public void onClick(View v) {
////        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
////        String item = mList.get(itemPosition);
////        Toast.makeText(mContext, item, Toast.LENGTH_LONG).show();
////    }
//
//
//    public static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private TextView mName;
//        private Button mEditButton;
//        private Button mOkButton;
//        private TextView mEmail;
//        private TextView mExperience;
//        private EditText mExpEdit;
//        private ResumeList listObj = new ResumeList();
//        private int positionOfResume=0;
//        private String imageURL;
//        private ImageView mImage;
//        String emailText;
//        Context c = itemView.getContext();
//        DbHelper dbObj = new DbHelper(c);
//        SQLiteDatabase mdb = dbObj.getReadableDatabase();
//        String url;
//        String name;
//
//        public HeaderViewHolder(View itemView,String email,ResumeHeader hObj) {
//            super(itemView);
//
//            mName = (TextView) itemView.findViewById(R.id.name_editText);
//            mEmail = (TextView) itemView.findViewById(R.id.email_editText);
//            mExperience = (TextView) itemView.findViewById(R.id.exp_tv);
//            mExpEdit = (EditText) itemView.findViewById(R.id.exp_editText);
//
//            mEditButton = (Button) itemView.findViewById(R.id.edit_button);
//            //mOkButton = (Button) itemView.findViewById(R.id.ok_button);
//            mImage = (ImageView) itemView.findViewById(R.id.imageView);
//            mImage.setClickable(true);
//            emailText= email;
//            name = hObj.getPersonName();
//
//            mEditButton.setOnClickListener(this);
//            //mOkButton.setOnClickListener(this);
//            mImage.setOnClickListener(this);
//            url = hObj.getImageURL();
//
//            //Log.d("adapter",""+dbObj.getHeaderRow(emailText).getPersonExperience());
////            mEmail.setText(dbObj.getHeaderRow(emailText).getPersonEmail());
////            mName.setText(hObj.getPersonName());
////            mExperience.setText(dbObj.getHeaderRow(emailText).getPersonExperience());
////            String url = dbObj.getHeaderRow(emailText).getImageURL();
//            mEmail.setText(dbObj.getHeaderRow(hObj).getPersonEmail());
//            mName.setText(hObj.getPersonName());
//            mExperience.setText(dbObj.getHeaderRow(hObj).getPersonExperience());
//            String url = dbObj.getHeaderRow(hObj).getImageURL();
//            Log.d("header view",""+url);
//
//            //Glide.with(c).load(url).into(mImage);
//            Glide.with(c).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(mImage) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    RoundedBitmapDrawable circularBitmapDrawable =
//                            RoundedBitmapDrawableFactory.create(c.getResources(), resource);
//                    circularBitmapDrawable.setCircular(true);
//                    mImage.setImageDrawable(circularBitmapDrawable);
//                }
//            });
//
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.edit_button:
//                    Log.d("editbutton","experience");
//
//                    Context context = itemView.getContext();
//                    Intent inten = new Intent(context,HeaderActivity.class);
//                    inten.putExtra("Email",emailText);
//                    inten.putExtra("image",url);
//                    inten.putExtra("name",name);
//                    context.startActivity(inten);
//                    break;
//                case R.id.ok_button:
//
//                    break;
//                case R.id.imageView:
//                     Intent i = new Intent(c,EditPhoto.class);
//                     i.putExtra("email",emailText);
//                     i.putExtra("name",name);
//                     c.startActivity(i);
//                     break;
//            }
//        }
//
//    }
//
//    public static class AboutMeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private TextView mLabel;
//        private TextView mDescription;
//        private Button mEditButton;
//        private Button mOkButton;
//        private EditText mDescEdit;
//        String emailText;
//        Context c = itemView.getContext();
//        DbHelper dbObj = new DbHelper(c);
//
//        public AboutMeViewHolder(View itemView,String email,ResumeHeader hObj) {
//            super(itemView);
//            mLabel = (TextView) itemView.findViewById(R.id.aboutme_tv);
//            mDescription = (TextView) itemView.findViewById(R.id.aboutme_desc_tv);
//            mEditButton = (Button) itemView.findViewById(R.id.aboutme_edit_button);
//            //mOkButton = (Button) itemView.findViewById(R.id.aboutme_ok_button);
//            mDescEdit =(EditText) itemView.findViewById(R.id.aboutme_editText);
//            mEditButton.setOnClickListener(this);
//            //mOkButton.setOnClickListener(this);
//            AboutMe aboutObj=new AboutMe();
//            aboutObj.setAboutmeText("fill here.....");
//            //dbObj.createAboutRow(aboutObj,hObj);
//            mDescription.setText(dbObj.getAboutRow(email).getAboutmeText());
//            emailText=email;
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
////                //case R.id.aboutme_edit_button:
////                    Intent aboutIntent = new Intent(c,AboutMeActivity.class);
////                    aboutIntent.putExtra("email",emailText);
////                    c.startActivity(aboutIntent);
////                    break;
////                //case R.id.aboutme_ok_button:
////                    break;
//            }
//        }
//    }
//    public static class InterestsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private TextView mInterestOne;
//        private TextView mInterestTwo;
//        private TextView mInterestThree;
//        private Button mEditInterests;
//
//
//        DbHelper dbObj;
//        Context c = itemView.getContext();
//        String emailText;
//
//        //private TextView mDescription;
//        public InterestsViewHolder(View itemView,String email,ResumeHeader hObj) {
//            super(itemView);
//
//            mInterestOne = (TextView) itemView.findViewById(R.id.interest1_tv);
//            mInterestTwo = (TextView) itemView.findViewById(R.id.interest2_tv);
//            mInterestThree = (TextView) itemView.findViewById(R.id.interest3_tv);
//            mEditInterests = (Button) itemView.findViewById(R.id.interests_edit_button);
//
//
//
//            mEditInterests.setOnClickListener(this);
//
//            emailText=email;
//            dbObj = new DbHelper(c);
//            Interests iObj = new Interests();
////            iObj.setInterestOne("fill here");
////            iObj.setInterestTwo("fill here");
////            iObj.setInterestThree("fill here");
////            //dbObj.createInterestsRow(iObj,hObj);
////            Log.d("interests holder",""+dbObj.getInterestsRow(email).getInterestOne());
////            mInterestOne.setText(dbObj.getInterestsRow(email).getInterestOne());
////            mInterestTwo.setText(dbObj.getInterestsRow(email).getInterestTwo());
////            mInterestThree.setText(dbObj.getInterestsRow(email).getInterestThree());
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch(v.getId()){
//                case R.id.interests_edit_button:
//                    Intent i = new Intent(c,InterestsActivity.class);
//                    i.putExtra("email",emailText);
//                    c.startActivity(i);
//                    break;
//                case R.id.interests_ok_button:
//                    break;
//
//            }
//        }
//    }
//    public static class ProjectsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView mProjectsTitle;
//        private TextView mNameLabel;
//        private TextView mNameDesc;
//        private TextView mDescLabel;
//        private TextView mDescDesc;
//        private TextView mDurationLabel;
//        private TextView mDurationDesc;
//        private EditText mNameEdit;
//        private EditText mDescEdit;
//        private EditText mDurationEdit;
//        private Button mEdit;
//        private Button mOk;
//        private Button mAddBtn;
//        private TextView mReadMore;
//        DbHelper dbObj;
//        Context c = itemView.getContext();
//        String emailText;
//
//        public ProjectsViewHolder(View itemView,String email,ResumeHeader hObj) {
//            super(itemView);
//            Log.d("adapter","projectholder");
//            mProjectsTitle = (TextView) itemView.findViewById(R.id.projects_label_tv);
//            mNameLabel = (TextView) itemView.findViewById(R.id.projectname_label_tv);
//            mNameDesc = (TextView) itemView.findViewById(R.id.projectname_desc_tv);
//            mDescLabel = (TextView) itemView.findViewById(R.id.projectdesc_label_tv);
//            mDescDesc = (TextView) itemView.findViewById(R.id.projectdesc_tv);
//            mDurationLabel = (TextView) itemView.findViewById(R.id.projectduration_label_tv);
//            mDurationDesc = (TextView) itemView.findViewById(R.id.projectduration_tv);
//            mNameEdit = (EditText) itemView.findViewById(R.id.projectname_desc_editText);
//            mDescEdit = (EditText)itemView.findViewById(R.id.projectdesc_editText);
//            mDurationEdit =(EditText)itemView.findViewById(R.id.projectduration_editText);
//            mEdit = (Button) itemView.findViewById(R.id.project_edit_button);
//            mOk = (Button) itemView.findViewById(R.id.project_ok_button);
//            mAddBtn = (Button)itemView.findViewById(R.id.addproject_button);
//            mReadMore = (TextView)itemView.findViewById(R.id.readmore_tv);
//
//            mEdit.setOnClickListener(this);
//            mOk.setOnClickListener(this);
//            mReadMore.setOnClickListener(this);
//            mAddBtn.setOnClickListener(this);
//            dbObj = new DbHelper(c);
//            if(dbObj.getProjectCount(email)==0){
//                mEdit.setEnabled(false);
//            }
//            else {
//                mEdit.setEnabled(true);
//
//
//                Projects obj = dbObj.getRecentProject(email);
//                mNameDesc.setText(obj.getProjectName());
//                mDescDesc.setText(obj.getDescription());
//                mDurationDesc.setText(obj.getProjectDuration());
//            }
//            emailText=email;
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch(v.getId()){
//                case R.id.project_edit_button:
//                    Intent i = new Intent(c,ProjectsActivity.class);
//                    i.putExtra("email",emailText);
//                    c.startActivity(i);
//                    break;
//                case R.id.project_ok_button:
//                    break;
//                case R.id.readmore_tv:
//                    Context c = itemView.getContext();
//                    Intent intent = new Intent(c,ProjectList.class);
//                    intent.putExtra("email",emailText);
//                    c.startActivity(intent);
//                    break;
//                case R.id.addproject_button:
//                    Context context = itemView.getContext();
//                    Intent inten = new Intent(context,ProjectDialog.class);
//                    inten.putExtra("email",emailText);
//                    context.startActivity(inten);
//                    break;
//
//            }
//
//        }
//
//
//    }
//    public static class EducationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        private TextView mEducationTitle;
//        private TextView mCourseLabel;
//        private TextView mCourseDesc;
//        private TextView mCollegeLabel;
//        private TextView mCollegeDesc;
//        private TextView mDurationLabel;
//        private TextView mDurationDesc;
//        private EditText mCourseET;
//        private EditText mCollegeET;
//        private EditText mDurationET;
//        private Button mEdit;
//        private Button mOk;
//        private Button mAddBtn;
//        private TextView mreadMore;
//        DbHelper dbObj;
//        Context c = itemView.getContext();
//        String emailText;
//        //private TextView mDescription;
//        public EducationViewHolder(View itemView,String email,ResumeHeader hObj) {
//            super(itemView);
//            mEducationTitle = (TextView) itemView.findViewById(R.id.education_label_tv);
//            mCourseLabel = (TextView) itemView.findViewById(R.id.coursename_label_tv);
//            mCourseDesc = (TextView) itemView.findViewById(R.id.coursename_desc_tv);
//            mCollegeLabel = (TextView) itemView.findViewById(R.id.collegename_label_tv);
//            mCollegeDesc = (TextView) itemView.findViewById(R.id.collegename_desc_tv);
//            mDurationLabel = (TextView) itemView.findViewById(R.id.duration_label_tv);
//            mDurationDesc = (TextView) itemView.findViewById(R.id.duration_desc_tv);
//            mCourseET = (EditText)itemView.findViewById(R.id.coursename_desc_editText);
//            mCollegeET = (EditText) itemView.findViewById(R.id.collegename_desc_editText);
//            mDurationET = (EditText)itemView.findViewById(R.id.duration_desc_editText);
//            mEdit = (Button) itemView.findViewById(R.id.education_edit_button);
//            mOk =(Button) itemView.findViewById(R.id.education_ok_button);
//            mAddBtn = (Button)itemView.findViewById(R.id.addeducation_button);
//            mreadMore = (TextView) itemView.findViewById(R.id.readmore_education_tv);
//
//            mEdit.setOnClickListener(this);
//            mOk.setOnClickListener(this);
//            mreadMore.setOnClickListener(this);
//            mAddBtn.setOnClickListener(this);
//            dbObj = new DbHelper(c);
//
//            if(dbObj.getEducationCount(email)==0){
//                mEdit.setEnabled(false);
//            }
//            else {
//                mEdit.setEnabled(true);
//
//
//                Education obj = dbObj.getRecentEducation(email);
//                mCourseDesc.setText(obj.getCourseOfStudy());
//                mCollegeDesc.setText(obj.getCollegeName());
//                mDurationDesc.setText(obj.getStudyDuration());
//            }
//            emailText=email;
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch(v.getId()){
//                case R.id.education_edit_button:
//                    Intent i = new Intent(c,EducationActivity.class);
//                    i.putExtra("email",emailText);
//                    c.startActivity(i);
//                    break;
//                case R.id.education_ok_button:
//                    break;
//                case R.id.readmore_education_tv:
//                    //Context c = itemView.getContext();
//                    Intent intent = new Intent(c,EducationList.class);
//                    intent.putExtra("email",emailText);
//                    c.startActivity(intent);
//                    break;
//                case R.id.addeducation_button:
//                    Intent inten = new Intent(c,EducationDialog.class);
//                    inten.putExtra("email",emailText);
//                    c.startActivity(inten);
//                    break;
//
//            }
//        }
//    }
//}