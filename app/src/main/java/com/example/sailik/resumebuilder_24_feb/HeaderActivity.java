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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class HeaderActivity extends AppCompatActivity {

    private EditText mExpEdit;
    private DbHelper dbObj;
    ResumeHeader hObj;
    String email;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        mExpEdit = (EditText) findViewById(R.id.exp_editText);
        mExpEdit.setFocusable(true);

        Intent i = getIntent();
        email = i.getExtras().getString("Email");
        name = i.getExtras().getString("name");
        String imageURL = i.getExtras().getString("image");


        dbObj = new DbHelper(this);
        hObj = new ResumeHeader();
        hObj.setPersonName(name);
        hObj.setImageURL(imageURL);
        hObj.setPersonEmail(email);
        mExpEdit.setText(dbObj.getHeaderRow(hObj).getPersonExperience());

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_header);
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
                break;
            case R.id.tick:
                String text = mExpEdit.getText().toString();
                dbObj.updateExperience(email,text);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
