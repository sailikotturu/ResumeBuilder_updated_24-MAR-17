package com.example.sailik.resumebuilder_24_feb;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {
    private EditText mTextEdit;
    private TextView mTextView;
    DbHelper dbObj;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        mTextEdit = (EditText)findViewById(R.id.aboutme_editText);
        mTextView = (TextView)findViewById(R.id.aboutme_desc_tv);
        dbObj = new DbHelper(this);
        Intent i = getIntent();
        email = i.getExtras().getString("email");
        if(dbObj.getAboutRow(email)!=null) {
            mTextEdit.setText(dbObj.getAboutRow(email).getAboutmeText());
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            Log.d("getsupportaction bar","not null");
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
                String text = mTextEdit.getText().toString();
                if(dbObj.getAboutRow(email)!=null) {
                    dbObj.updateAboutMe(email, text);
                }
                else{
                    dbObj.createAboutRow(text,email);
                }
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
