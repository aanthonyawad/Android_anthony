package com.example.anthony.stupidness;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by anthony on 7/7/17.
 */

public class HomePage extends AppCompatActivity  implements GoogleApiClient.OnConnectionFailedListener {
    private static final String MY_PREFS_NAME = "MyPrefsFile";
    TextView tvName,tvEmail;
    GoogleApiClient mGoogleApiClient;
    SharedPreferences prefs ;
    GoogleSignInOptions gso;
    SharedPreferences.Editor editor;


  //  String restoredText = prefs.getString("text", null);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        initialise();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    }


private void initialise(){
    prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    tvName = (TextView) findViewById(R.id.tvUserName);
    tvEmail = (TextView) findViewById(R.id.tvUserEmail);

    //  String restoredText = prefs.getString("text", null);
    if(prefs.getString("id",null).equals("gmail")) {

        tvName.setText(prefs.getString("name","no name set"));

        tvEmail.setText(prefs.getString("email","no email set"));
    }else if(prefs.getString("id",null).equals("fb")){

        tvName.setText(prefs.getString("name","no fb name set"));

        tvEmail.setText(prefs.getString("email","no fb email set"));
    }
}
    public void disconnectFromFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();

            }
        }).executeAsync();
    }
    public void disconnectFromGmail(){
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();

                    }
                });

    }

public void logout(View view){
    if(prefs.getString("id",null).equals("fb"))
    disconnectFromFacebook();
    else if (prefs.getString("id",null).equals("gmail"))
        disconnectFromGmail();
    editor.putString("id", "main");
    editor.commit();
    Intent i= new Intent(HomePage.this, MainActivity.class);
    startActivity(i);
}

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
