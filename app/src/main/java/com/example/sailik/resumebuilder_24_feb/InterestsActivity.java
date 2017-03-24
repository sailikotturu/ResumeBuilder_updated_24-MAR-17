package com.example.sailik.resumebuilder_24_feb;

import android.app.Activity;
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
import android.widget.TextView;

import java.util.ArrayList;

public class InterestsActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelper dbObj;
    String email;
    InterestsAdapter adapter;
    RecyclerView mRecyclerView;
    ArrayList<Interests> allInterests;
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);


        mFab = (FloatingActionButton)findViewById(R.id.fab_interests);
        mFab.setOnClickListener(this);
        dbObj=new DbHelper(this);
        Intent getI = getIntent();
        email = getI.getExtras().getString("email");


        if(dbObj.getInterestsCount(email)>0) {
            allInterests = dbObj.getAllInterests(email);
        }
        else {
            allInterests = new ArrayList<Interests>();
        }


        adapter = new InterestsAdapter(this, allInterests,email);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_interests);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_interestlist);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        allInterests = dbObj.getAllInterests(email);
        adapter.add(allInterests);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab_interests:
                Intent inten = new Intent(this,InterestsDialog.class);
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
