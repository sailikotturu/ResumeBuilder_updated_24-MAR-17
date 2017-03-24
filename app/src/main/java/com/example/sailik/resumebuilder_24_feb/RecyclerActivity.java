package com.example.sailik.resumebuilder_24_feb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RecyclerActivity extends AppCompatActivity{
//    private EditText mAboutMeET;
//    private Button mAboutEdit;
//    private Button mAboutOk;
//    private TextView mABoutMeDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

//        mAboutMeET = (EditText) findViewById(R.id.aboutme_editText);
//        mAboutEdit = (Button) findViewById(R.id.aboutme_edit_button);
//        mAboutOk = (Button) findViewById(R.id.aboutme_ok_button);
//        mABoutMeDesc = (TextView) findViewById(R.id.aboutme_tv);
//
//        mAboutEdit.setOnClickListener(this);
//        mAboutOk.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
//        DifferentRowAdapter adapter = new DifferentRowAdapter(DummyData.getData());
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);
    }

    //@Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.aboutme_edit_button:
//                mAboutEdit.setEnabled(false);
//                mAboutOk.setVisibility(View.VISIBLE);
//                mABoutMeDesc.setVisibility(View.INVISIBLE);
//                mAboutMeET.setVisibility(View.VISIBLE);
//                String text = mABoutMeDesc.getText().toString();
//                mAboutMeET.setText(text);
//                break;
//            case R.id.aboutme_ok_button:
//                text = mAboutMeET.getText().toString();
//                mAboutMeET.setVisibility(View.INVISIBLE);
//                mABoutMeDesc.setVisibility(View.VISIBLE);
//                mABoutMeDesc.setText(text);
//                mAboutOk.setVisibility(View.INVISIBLE);
//                mAboutEdit.setVisibility(View.VISIBLE);
//                break;
//
//        }
//    }
}
