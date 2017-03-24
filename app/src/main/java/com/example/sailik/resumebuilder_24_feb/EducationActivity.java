package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class EducationActivity extends AppCompatActivity implements View.OnClickListener {
//    private EditText mCourse;
//    private EditText mCollege;
//    private EditText mDuration;
//    private Button mOkBtn;
    String email;
    private DbHelper dbObj;
    RecyclerView mRecyclerView;
    FloatingActionButton mFab;
    EducationAdapter adapter;
    ArrayList<Education> educationList;
    int currentVisiblePosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

//        mCourse = (EditText)findViewById(R.id.coursename_desc_editText);
//        mCollege = (EditText)findViewById(R.id.collegename_desc_editText);
//        mDuration = (EditText)findViewById(R.id.duration_desc_editText);
//        mOkBtn = (Button)findViewById(R.id.education_ok_button);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView_education);
        mFab = (FloatingActionButton)findViewById(R.id.fab_education);
        dbObj=new DbHelper(this);

        Intent getI = getIntent();
        email = getI.getExtras().getString("email");
        mFab.setOnClickListener(this);

        if(dbObj.getEducationCount(email)>0) {
            educationList = dbObj.getEducationList(email);
        }
        else{
            educationList = new ArrayList<Education>();
        }

        adapter = new EducationAdapter(this,educationList,email);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_education);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_educationlist);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.fab_education:
                Intent inten = new Intent(this,EducationDialog.class);
                inten.putExtra("email",email);
                startActivity(inten);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        educationList = new ArrayList<Education>();
        if(dbObj.getEducationCount(email)>0) {
            educationList = dbObj.getEducationList(email);
        }
        adapter.add(educationList);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();

        }
        return super.onOptionsItemSelected(item);

    }
}
