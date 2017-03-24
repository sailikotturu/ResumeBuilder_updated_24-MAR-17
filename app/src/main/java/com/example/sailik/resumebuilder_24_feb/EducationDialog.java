package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class EducationDialog extends AppCompatActivity {
    private EditText mCourse;
    private EditText mCollege;
    private EditText mDuration;
//    private Button mOkBtn;
//    private Button mCancelBtn;
    private DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_dialog);

        mCourse = (EditText) findViewById(R.id.coursename_desc_editText);
        mCollege = (EditText) findViewById(R.id.collegename_desc_editText);
        mDuration = (EditText) findViewById(R.id.duration_desc_editText);

        dbObj = new DbHelper(this);
        Intent getI = getIntent();
        email=getI.getStringExtra("email");

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_addeducation);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
            case R.id.tick:
                String course = mCourse.getText().toString();
                String college = mCollege.getText().toString();
                String duration = mDuration.getText().toString();
                Education obj = new Education();
                obj.setCourseOfStudy(course);
                obj.setCollegeName(college);
                obj.setStudyDuration(duration);
                dbObj.createEducationRow(obj,email);
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
