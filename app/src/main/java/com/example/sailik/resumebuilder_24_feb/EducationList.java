package com.example.sailik.resumebuilder_24_feb;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class EducationList extends ListActivity implements View.OnClickListener {

    private Button mOkBtn;
    private Button mAddBtn;

    private DbHelper helper;
    private CursorAdapter dataSource;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_list);

        mOkBtn = (Button) findViewById(R.id.educationlist_ok_btn);
        mAddBtn = (Button) findViewById(R.id.educationlist_add_button);
        mOkBtn.setOnClickListener(this);
        mAddBtn.setOnClickListener(this);



        helper = new DbHelper(this);

        Intent in = getIntent();
        email = in.getExtras().getString("email");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.educationlist_ok_btn:
                finish();
                break;
            case R.id.educationlist_add_button:
                Intent inten = new Intent(this,EducationDialog.class);
                inten.putExtra("email",email);
                startActivity(inten);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Cursor c = helper.getAllEducation(email);


        String[] from = {helper.KEY_EDUCATION_COURSE,helper.KEY_EDUCATION_COLLEGE,helper.KEY_EDUCATION_DURATION};
        int[] to = new int[] { R.id.coursename_desc_tv, R.id.collegename_desc_tv,R.id.duration_desc_tv };
        dataSource = new SimpleCursorAdapter(this,
                R.layout.educationlist_data,c,from,to);



        this.setListAdapter(dataSource);
    }
}
