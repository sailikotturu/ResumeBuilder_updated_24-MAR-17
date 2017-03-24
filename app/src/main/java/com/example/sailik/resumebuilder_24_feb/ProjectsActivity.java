package com.example.sailik.resumebuilder_24_feb;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ProjectsActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelper dbObj;
    String email;
    ProjectsAdapter adapter;
    RecyclerView mRecyclerView;
    ArrayList<Projects> allProjects;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);


        mFab = (FloatingActionButton)findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        dbObj=new DbHelper(this);
        Intent getI = getIntent();
        email = getI.getExtras().getString("email");

        if(dbObj.getProjectCount(email)>0) {
            allProjects = dbObj.getProjectList(email);
        }
        else {
            allProjects = new ArrayList<Projects>();
        }

        adapter = new ProjectsAdapter(this, allProjects,email);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_projectlist);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            Log.d("getsupportaction bar","not null");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(dbObj.getProjectCount(email)>0) {
            allProjects = dbObj.getProjectList(email);
        }
        adapter.add(allProjects);


    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab:
                Intent inten = new Intent(this,ProjectDialog.class);
                inten.putExtra("email",email);
                startActivity(inten);
                break;
        }
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
