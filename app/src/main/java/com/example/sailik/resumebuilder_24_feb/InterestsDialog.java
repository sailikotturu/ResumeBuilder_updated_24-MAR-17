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

public class InterestsDialog extends AppCompatActivity {

    private EditText mAddInterestET;
    private DbHelper dbObj;
    int position;
    String email;
    ArrayList<Interests> interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests_dialog);

        mAddInterestET = (EditText)findViewById(R.id.addinterest_editText);
        dbObj = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_addinterests);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        email = i.getExtras().getString("email");
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
                String text = mAddInterestET.getText().toString();
                dbObj.createInterestsRow(text,email);
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
