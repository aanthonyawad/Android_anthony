package com.example.anthony.stupidness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by anthony on 7/6/17.
 */

public class Login extends AppCompatActivity {
    private EditText etUsername , etPassword;
    private Intent i;
    private TextView tvState,tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initialise();
        addListeners();
    }


    private void initialise(){
        etUsername= (EditText)findViewById(R.id.etUsername);
        etPassword= (EditText)findViewById(R.id.etPassword);
        tvState = (TextView)findViewById(R.id.tvState);
        tvLogin = (TextView)findViewById(R.id.tvAccLogin);
    }
public void addListeners(){
    tvLogin.setOnClickListener(clickListener);

}

    private View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.tvAccLogin :
                login();
                    break;

            }
        }
    };

    private boolean checkLoginCredentials() {
        //to check if the user and pass are correct
        boolean result =false;


        return result;
    }

    private void login(){
        if(!(etUsername.getText().toString().isEmpty() || etUsername.getText().toString().length() == 0 || etUsername.getText().toString().equals("") || etUsername.getText().toString() == null)){
            //enter if user is not empty
            if(!(etPassword.getText().toString().isEmpty() || etPassword.getText().toString().length() == 0 || etPassword.getText().toString().equals("") || etPassword.getText().toString() == null)){//enter if password is not empty
                if(checkLoginCredentials()) {
                    i = new Intent(Login.this, Login.class);
                    startActivity(i);
                }else {
                    tvState.setText("Wrong username pass combination");
                    etPassword.setText("");
                }
            }else{
                tvState.setText("Plz input your stupid password");

            }
        }
        else{
            tvState.setText("Plz input your stupid username");


        }


    }
}

