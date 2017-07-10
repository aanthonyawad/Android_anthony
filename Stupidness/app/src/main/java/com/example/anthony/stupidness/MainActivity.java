package com.example.anthony.stupidness;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by anthony on 7/6/17.
 */

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener  {
    ProgressDialog progressDialog;
    private AccessTokenTracker accessTokenTracker ;
    AccessToken accessToken;
    private static final int RC_SIGN_IN =100 ;
    private TextView tvLogin,tvGplus,tvRegister,tvForgotPass;
    private TextView tvFB;
    private Intent i;
    private List<String> permissionNeeds= Arrays.asList("user_photos", "friends_photos", "email", "user_birthday", "user_friends");
   GoogleApiClient mGoogleApiClient;
   public static CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.mainactivity);

        // Configure sign-in to request the user's ID, email address, and basic profile. ID and
        // basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();



        // Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();



        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // login successful

//                        progressDialog = new ProgressDialog(MainActivity.this);
//                        progressDialog.setMessage("Procesando datos...");
//                        progressDialog.show();
                        String accessToken = loginResult.getAccessToken().getToken();
                        Log.i("accessToken", accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("LoginActivity", response.toString());
                                // Get facebook data from login
                                Bundle bFacebookData = getFacebookData(object);
                               
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                        request.setParameters(parameters);
                        request.executeAsync();
                    }




                    @Override
                    public void onCancel() {
                        // login cancelled
                        System.out.println("CAncel");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // login error
                        System.out.println("error");
                    }
                });



        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken();

        initialise();
        addListeners();
    }



    private void initialise() {
            tvLogin = (TextView)findViewById(R.id.tvLogin);
            tvGplus = (TextView)findViewById(R.id.tvGplus);
            tvFB = (TextView)findViewById(R.id.tvFB);
            tvRegister = (TextView)findViewById(R.id.tvRegister);
            tvForgotPass = (TextView)findViewById(R.id.tvForgotPass);

    }

    private void addListeners(){
        tvLogin.setOnClickListener(clickListener);
        tvGplus.setOnClickListener(clickListener);
        tvFB.setOnClickListener(clickListener);
        tvRegister.setOnClickListener(clickListener);
        tvForgotPass.setOnClickListener(clickListener);


    }


    private View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.tvLogin :
                    i = new Intent(MainActivity.this,Login.class);
                    startActivity(i);

                    break;

                case R.id.tvGplus :
                   Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                    startActivityForResult(signInIntent, RC_SIGN_IN);

                    break;

                case R.id.tvFB :
                    System.out.println("kabas button");



                    LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

                    break;

                case R.id.tvRegister :
                    i = new Intent(MainActivity.this,Register.class);
                    startActivity(i);
                    break;

                case R.id.tvForgotPass :

                    break;

            }
        }
    };



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        System.out.println("FAILED CONNECTION");
    }


    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            System.out.println("Fet bel result");
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                GoogleSignInAccount acct = result.getSignInAccount();

                // Get account information
                System.out.println("Display name " + acct.getDisplayName());
                System.out.println("Email" + acct.getEmail());
                Intent homePage = new Intent(MainActivity.this , HomePage.class);
                    homePage.putExtra("name",acct.getDisplayName());
                homePage.putExtra("email",acct.getEmail());
                startActivity(homePage);

            } else {
                System.out.println(result.getStatus().getStatusMessage());
                System.out.println(result.isSuccess());
                System.out.println("Error result is not success");


            }
        }

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        try {

            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));


        }
        catch(JSONException e) {
            //Log.d(TAG,"Error parsing JSON");
        }
        return bundle;
    }



}
