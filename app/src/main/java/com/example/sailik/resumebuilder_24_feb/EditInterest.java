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

public class EditInterest extends AppCompatActivity {

    private EditText mInterestET;
//    private EditText mDescET;
//    private EditText mDurationET;
    private DbHelper dbObj;
    int position;
    String email;
    ArrayList<Interests> interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_interest);

        mInterestET = (EditText)findViewById(R.id.interest_editText);
        interests = new ArrayList<Interests>();
        dbObj = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar_interests);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        position = Integer.parseInt(i.getExtras().getString("position"));
        email = i.getExtras().getString("email");
        interests = dbObj.getAllInterests(email);

        mInterestET.setText(interests.get(position).getInterestText());

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
                String text = mInterestET.getText().toString();
                dbObj.updateInterestByPosition(position,email,text);
                this.finish();
                break;

        }
        return super.onOptionsItemSelected(item);

    }
}
