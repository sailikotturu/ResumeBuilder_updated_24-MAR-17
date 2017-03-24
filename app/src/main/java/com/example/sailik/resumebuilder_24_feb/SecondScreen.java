package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class SecondScreen extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    //    private RecyclerView mRecyclerView;
//    private LinearLayoutManager mLinearLayoutManager;
    private ImageView profileImage;
    //private TextView Name;
    private TextView profileName;
    private TextView email;
    private EditText experience;
    private Button signout;
    private Button edit_exp;
    private GoogleApiClient mGoogleApiClient;
    String CUSTOM_ACTION = "SIGN_OUT";
    private Button revoke;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_list_row);

        profileImage = (ImageView) findViewById(R.id.imageView);
        profileName = (TextView) findViewById(R.id.name_editText);
        email = (TextView) findViewById(R.id.email_editText);
        experience = (EditText) findViewById(R.id.exp_editText);
        //signout = (Button) findViewById(R.id.signout_button);
        edit_exp = (Button) findViewById(R.id.edit_button);
        //revoke =(Button) findViewById(R.id.revoke_button);

        signout.setOnClickListener(this);
        edit_exp.setOnClickListener(this);
        revoke.setOnClickListener(this);

        String action = getIntent().getAction();
        Intent details;
        if (action.equals("PERSON_DETAILS")||action.equals("FB_LOGIN")) {
            details = getIntent();
            if(action.equals("FB_LOGIN")){
                signout.setVisibility(View.INVISIBLE);
            }
            String personName = details.getStringExtra("Name");
            String imageURL = details.getStringExtra("image_url");
            String personEmail = details.getStringExtra("email");

            profileName.setText(personName);
            email.setText(personEmail);
            Glide.with(getApplicationContext()).load(imageURL)
                    .thumbnail(0.5f)
                    .crossFade()
                    //.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profileImage);
        }
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mLinearLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d("second screen", "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.signout_button:
////                Intent i = new Intent(this, MainActivity.class);
////
////                i.setAction(CUSTOM_ACTION);
////                startActivity(i);
//                Auth.GoogleSignInApi.signOut(mGoogleApiClient);
//                Intent signout = new Intent(this, MainActivity.class);
//                startActivity(signout);
//                break;
            case R.id.edit_button:
                edit_exp.setVisibility(v.VISIBLE);
                break;
//            case R.id.revoke_button:
//                Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient);
//                Intent revoke = new Intent(this, MainActivity.class);
//                startActivity(revoke);
//                break;

        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SecondScreen Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
