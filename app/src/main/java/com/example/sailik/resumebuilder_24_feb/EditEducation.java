package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;

public class EditEducation extends AppCompatActivity {

    private EditText mCourseET;
    private EditText mCollegeET;
    private EditText mDurationET;
    private DbHelper dbObj;
    int position;
    String email;
    ArrayList<Education> e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_education);
        mCourseET = (EditText)findViewById(R.id.coursename_desc_editText);
        mCollegeET = (EditText)findViewById(R.id.collegename_desc_editText);
        mDurationET = (EditText)findViewById(R.id.duration_desc_editText);

        e = new ArrayList<Education>();

        dbObj = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_education);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        position = Integer.parseInt(i.getExtras().getString("position"));
        email = i.getExtras().getString("email");
        e = dbObj.getEducationList(email);

        mCourseET.setText(e.get(position).getCourseOfStudy());
        mCollegeET.setText(e.get(position).getCollegeName());
        mDurationET.setText(e.get(position).getStudyDuration());
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
                String course = mCourseET.getText().toString();
                String college = mCollegeET.getText().toString();
                String duration = mDurationET.getText().toString();
                dbObj.updateEducationByPosition(position,email,course,college,duration);
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
