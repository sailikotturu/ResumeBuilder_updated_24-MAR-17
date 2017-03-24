package com.example.sailik.resumebuilder_24_feb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    @SuppressWarnings("FieldCanBeLocal")
    private SignInButton btnSignIn;

    @SuppressWarnings("FieldCanBeLocal")
    private GoogleSignInOptions gso;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;
    private static final int F_SIGN_IN = 005;
    private LoginButton flogin;
    private CallbackManager callbackManager;



    // Your Facebook APP ID
    private static String APP_ID = "308180782571605";



    PrefUtil prefUtil = new PrefUtil(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        flogin = (LoginButton) findViewById(R.id.login_button);
        flogin.setReadPermissions(Arrays.asList(
                "public_profile", "email"));
        flogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d("register call back","success");
                String accessToken = loginResult.getAccessToken().getToken();

                        // save accessToken to SharedPreference
                prefUtil.saveAccessToken(accessToken);

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject,GraphResponse response) {
                        // Getting FB User Data
                        getFacebookData(jsonObject);
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();
            }


            @Override
            public void onCancel () {
                        Log.d(TAG, "Login attempt cancelled.");
                    }

            @Override
            public void onError (FacebookException e){
                        e.printStackTrace();
                        Log.d(TAG, "Login attempt failed.");
                        deleteAccessToken();
            }
        });

        btnSignIn.setOnClickListener(this);

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_WIDE);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void getFacebookData(JSONObject object) {

        Bundle bundle = new Bundle();
        String photoURL;
        String name;
        String email;

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                bundle.putString("profile_pic", profile_pic.toString());
                photoURL = profile_pic.toString();
                bundle.putString("idFacebook", id);
                if (object.has("first_name"))
                    bundle.putString("first_name", object.getString("first_name"));
                if (object.has("last_name"))
                    bundle.putString("last_name", object.getString("last_name"));
                if (object.has("email"))
                    bundle.putString("email", object.getString("email"));
                if (object.has("gender"))
                    bundle.putString("gender", object.getString("gender"));

                name = ""+object.getString("first_name")+" "+object.getString("last_name");
                email = ""+object.getString("email");

                prefUtil.saveFacebookUserInfo(object.getString("first_name"),
                        object.getString("last_name"),object.getString("email"),
                        object.getString("gender"), profile_pic.toString());
                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                i.setAction("FB_LOGIN");
                i.putExtra("image_url",photoURL);
                i.putExtra("Name",name);
                i.putExtra("email",email);
                LoginManager.getInstance().logOut();
                startActivity(i);
            }
            catch (MalformedURLException e) {
                e.printStackTrace();

            }

        }
        catch (Exception e) {
            Log.d(TAG, "BUNDLE Exception : "+e.toString());
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        Log.d(TAG,"signOut");
//                        //updateUI(false);
//                        Toast.makeText(MainActivity.this,"logged out!!",Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//    private void revokeAccess() {
//        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        //updateUI(false);
//                        Toast.makeText(MainActivity.this,"logged out!!",Toast.LENGTH_SHORT).show();
//                        Log.d(TAG,"revoke");
//                    }
//                });
//    }
    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            String personName = acct.getDisplayName();
            String personPhotoUrl=null;
            if(acct.getPhotoUrl()!=null) {
                personPhotoUrl = acct.getPhotoUrl().toString();
            }  else{
               personPhotoUrl="https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT71_JWJnLpdRmI-9oFxyl0vtdl15wjMWcxtC5xf0bQl8r2Vc0OOA";
            }

            String email = acct.getEmail();

            Intent personDetails = new Intent(MainActivity.this,ProfileActivity.class);
            personDetails.setAction("PERSON_DETAILS");
            personDetails.putExtra("Name",personName);
            personDetails.putExtra("image_url",personPhotoUrl);
            personDetails.putExtra("email",email);
            startActivity(personDetails);

        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_sign_in:
                signIn();
                break;

            case R.id.login_button:

                flogin.setReadPermissions(Arrays.asList(
                        "public_profile", "email"));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        else{
            //super.onActivityResult(requestCode, resultCode, data);
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }
    private void deleteAccessToken() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    //User logged out
                    prefUtil.clearToken();
                    LoginManager.getInstance().logOut();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

//    private void updateUI(boolean isSignedIn) {
//
//    }

    @Override
    protected void onResume() {

        super.onResume();
    }
}
