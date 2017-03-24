package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import static android.R.color.black;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    String CUSTOM_ACTION = "SIGN_OUT";
    private GoogleApiClient client;
    private GoogleSignInOptions gso;

    private ImageView mPicture;
    private TextView mName;
    private TextView mEmail;
    private TextView mExperience;


    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtEmail;
    private Toolbar toolbar;
    private ResumeHeader hObj;
    public static int navItemIndex = 0;

    private SQLiteDatabase mdb;
    private String editEmail;
    private String imageURL;
    private String personName;
    private String personEmail;
    private DbHelper dbObj;
    private ResumeAdapter adapter;
    private Handler mHandler;
    private RecyclerView mRecyclerView;

    static final int RESULT_LOAD_IMG = 6;
    private LinearLayout header;
    private RelativeLayout aboutMe;
    private TextView mAboutMe;
    private TextView mProjects_tv;
    private TextView mEducation_tv;
    private TextView mInterests_tv;
    String personExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ProfileActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        header = (LinearLayout)findViewById(R.id.resume_header);
        aboutMe = (RelativeLayout)findViewById(R.id.resume_aboutme);
        mAboutMe = (TextView)findViewById(R.id.aboutme_desc_tv);
        mProjects_tv = (TextView)findViewById(R.id.projects_textview);
        mEducation_tv = (TextView)findViewById(R.id.education_textview);
        mInterests_tv = (TextView)findViewById(R.id.interests_textview);

        header.setOnClickListener(this);
        aboutMe.setOnClickListener(this);
        mProjects_tv.setOnClickListener(this);
        mEducation_tv.setOnClickListener(this);
        mInterests_tv.setOnClickListener(this);

        mPicture = (ImageView)findViewById(R.id.imageView);
        mName = (TextView)findViewById(R.id.name_editText);
        mEmail = (TextView)findViewById(R.id.email_editText);
        mExperience = (TextView)findViewById(R.id.exp_tv);

        mPicture.setOnClickListener(this);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navHeader = navigationView.getHeaderView(0);
        imgProfile =(ImageView) navigationView.getHeaderView(0).findViewById(R.id.img_profile);
        txtName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.name);
        txtEmail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.email);




        String action = getIntent().getAction();
        Intent details;

        if (action.equals("PERSON_DETAILS")||action.equals("FB_LOGIN")) {
            details = getIntent();

            personName = details.getStringExtra("Name");
            imageURL = details.getStringExtra("image_url");
            personEmail = details.getStringExtra("email");

            editEmail = "";
            int i=0;
            while(personEmail.charAt(i)!='@'){
                editEmail = editEmail+personEmail.charAt(i);
                i++;
            }
            hObj = new ResumeHeader(personName,personEmail,imageURL,"");
            dbObj = new DbHelper(this);
            mdb = dbObj.getReadableDatabase();
            dbObj.createHeaderRow(hObj);


        }
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {
            case R.id.nav_resume:
                drawer.closeDrawers();
                return true;
            case R.id.nav_about:
                startActivityForResult(new Intent(ProfileActivity.this, AboutScreen.class),2);

                drawer.closeDrawers();
                return true;

        }

        if (menuItem.isChecked()) {
            menuItem.setChecked(false);
        } else {
            menuItem.setChecked(true);
        }
        menuItem.setChecked(true);
        return true;
    }

    private void loadNavHeader() {

        txtName.setText(""+personName);
        txtName.setTextColor(getResources().getColor(black));

        txtEmail.setText(""+personEmail);
        txtEmail.setTextColor(getResources().getColor(black));
        ResumeHeader hObj = new ResumeHeader();
        hObj.setPersonEmail(personEmail);
        hObj.setPersonName(personName);
        String url = dbObj.getHeaderRow(hObj).getImageURL();

        Glide.with(this).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imgProfile) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(ProfileActivity.this.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imgProfile.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.profile, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            finish();

                        }
                    });
            Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d("second screen", "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void onClick(View v) {
        Log.d("profileactivity","onclick");
        switch (v.getId()) {
            case R.id.resume_header:
                Intent in = new Intent(this,HeaderActivity.class);
                in.putExtra("Email",personEmail);
                in.putExtra("name",personName);
                startActivity(in);
                break;
            case R.id.resume_aboutme:
                Intent inten = new Intent(this,AboutMeActivity.class);
                inten.putExtra("email",personEmail);
                startActivity(inten);
                break;
            case R.id.projects_textview:
                Intent i = new Intent(this,ProjectsActivity.class);
                i.putExtra("email",personEmail);
                startActivity(i);
                break;
            case R.id.education_textview:
                Intent intent = new Intent(this,EducationActivity.class);
                intent.putExtra("email",personEmail);
                startActivity(intent);
                break;
            case R.id.interests_textview:
                Intent inte = new Intent(this,InterestsActivity.class);
                inte.putExtra("email",personEmail);
                startActivity(inte);
                break;
            case R.id.imageView:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent,RESULT_LOAD_IMG);
                break;
        }

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                && null != data){

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Glide.with(this).load(picturePath).asBitmap().centerCrop().into(new BitmapImageViewTarget(mPicture) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mPicture.setImageDrawable(circularBitmapDrawable);
                }
            });

            String path = selectedImage+"";
            dbObj.updatePhoto(personName,path);

        }
        else if (requestCode == RESULT_LOAD_IMG && resultCode != RESULT_OK
                && null != data){
//            Toast.makeText(this, getResources().getString(R.string.cancel_toast),
//                    Toast.LENGTH_LONG).show();
        }
        else if(requestCode==2)
        {
            String message=data.getStringExtra("MESSAGE");
            if(message.equals("logout")){
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                finish();

                            }
                        });
            }

        }
        else{

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
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("profileactivity","onresume");

        imageURL = dbObj.getHeaderRow(hObj).getImageURL();
        personName = dbObj.getHeaderRow(hObj).getPersonName();
        personEmail = dbObj.getHeaderRow(hObj).getPersonEmail();
        personExp = dbObj.getHeaderRow(hObj).getPersonExperience();
        if(dbObj.getAboutRow(personEmail)!=null) {
            mAboutMe.setText(dbObj.getAboutRow(personEmail).getAboutmeText());
        }
        Glide.with(this).load(imageURL).asBitmap().centerCrop().into(new BitmapImageViewTarget(mPicture) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                mPicture.setImageDrawable(circularBitmapDrawable);
            }
        });
        mName.setText(personName);
        mEmail.setText(personEmail);
        mExperience.setText(personExp);
        loadNavHeader();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void onImageClick(){

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
