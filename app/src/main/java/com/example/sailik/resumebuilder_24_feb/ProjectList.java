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

import java.util.ArrayList;
import java.util.List;

public class ProjectList extends ListActivity implements View.OnClickListener {


    private Button mOkBtn;
    private Button mAddBtn;
    private SQLiteDatabase database;

    private CursorAdapter dataSource;
    private static final String fields[] = { "projectname", "projectdesc","projectduration"};
    String email;
    private DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        mOkBtn = (Button)findViewById(R.id.projectlist_ok_btn);
        mAddBtn = (Button)findViewById(R.id.projectlist_add_button);
        //mTitleTV = (TextView) findViewById(R.id.project_tv);
        mOkBtn.setOnClickListener(this);
        mAddBtn.setOnClickListener(this);

        helper = new DbHelper(this);

        Intent in = getIntent();
        email = in.getExtras().getString("email");

        //List<Projects> pList = helper.getAllProjects(email);

        database = helper.getReadableDatabase();
        Cursor c = helper.getAllProjects(email);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.projectlist_ok_btn:
                finish();
                break;
            case R.id.projectlist_add_button:
                Intent inten = new Intent(this,ProjectDialog.class);
                inten.putExtra("email",email);
                startActivity(inten);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        database = helper.getReadableDatabase();
        Cursor c = helper.getAllProjects(email);
        String[] from = {helper.KEY_PROJECT_NAME,helper.KEY_PROJECT_DESC,helper.KEY_PROJECT_DURATION};
        int[] to = new int[] { R.id.projectname_desc_tv, R.id.projectdesc_tv,R.id.projectduration_tv };
        dataSource = new SimpleCursorAdapter(this,
                R.layout.projectlist_data,c,from,to);
        this.setListAdapter(dataSource);
    }
}
