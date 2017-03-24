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

public class EditProject extends AppCompatActivity {

    private EditText mNameET;
    private EditText mDescET;
    private EditText mDurationET;
    private DbHelper dbObj;
    int position;
    String email;
    ArrayList<Projects> p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);

        mNameET = (EditText)findViewById(R.id.projectname_desc_editText);
        mDescET = (EditText)findViewById(R.id.projectdesc_editText);
        mDurationET = (EditText)findViewById(R.id.projectduration_editText);

        p = new ArrayList<Projects>();

        dbObj = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_project);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            Log.d("getsupportaction bar","not null");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        position = Integer.parseInt(i.getExtras().getString("position"));
        email = i.getExtras().getString("email");
        p = dbObj.getProjectList(email);

        mNameET.setText(p.get(position).getProjectName());
        mDescET.setText(p.get(position).getDescription());
        mDurationET.setText(p.get(position).getProjectDuration());


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
                String name = mNameET.getText().toString();
                String description = mDescET.getText().toString();
                String duration = mDurationET.getText().toString();
                dbObj.updateProjectByPosition(position,email,name,description,duration);
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
