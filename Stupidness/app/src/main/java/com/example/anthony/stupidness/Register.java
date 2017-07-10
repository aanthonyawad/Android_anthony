package com.example.anthony.stupidness;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by anthony on 7/6/17.
 */

public class Register extends AppCompatActivity {
    private   EditText etFname,etLname,etUsername,etPassword,etEmail;
    private TextView tvRegister,tvRegisterState;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initialise();
        addListeners();
    }
    //initialise the vars
private void initialise(){

    etFname =(EditText)findViewById(R.id.RegisterFname);
    etLname =(EditText)findViewById(R.id.RegisterLname);
    etUsername =(EditText)findViewById(R.id.ReisterUsername);
    etPassword =(EditText)findViewById(R.id.RegisterPassword);
    etEmail =(EditText)findViewById(R.id.RegisterEmail);
    tvRegister= (TextView)findViewById(R.id.AccRegister);
    tvRegisterState= (TextView)findViewById(R.id.tvRegisterState);
}

//add the listeners
public void addListeners(){
        tvRegister.setOnClickListener(clickListener);

    }

 //Register the account
public void Register(){

    if(!(etFname.getText().toString().isEmpty() || etFname.getText().toString().length() == 0 || etFname.getText().toString().equals("") || etFname.getText().toString() == null)){
        //enter if fname not empty

        if(!(etLname.getText().toString().isEmpty() || etLname.getText().toString().length() == 0 || etLname.getText().toString().equals("") || etLname.getText().toString() == null)){
            // enter if lname not empty
            if(!(etEmail.getText().toString().isEmpty() || etEmail.getText().toString().length() == 0 || etEmail.getText().toString().equals("") || etEmail.getText().toString() == null)){
                //enter if email is not empty
               if(!(etUsername.getText().toString().isEmpty() || etUsername.getText().toString().length() == 0 || etUsername.getText().toString().equals("") || etUsername.getText().toString() == null)){
                   //enter if username if not empty
                    if(!(etPassword.getText().toString().isEmpty() || etPassword.getText().toString().length() == 0 || etPassword.getText().toString().equals("") || etPassword.getText().toString() == null)){
                        // enter if password not empty
                        // wich means all is set
                       if(CheckRegisterCredentials()){

                       }else {
                           //something went wrong
                           tvRegisterState.setText("stupid Username already taken");

                       }


                    }
                    else{
                        //enter if password is empty
                        tvRegisterState.setText("stupid Password empty");
                    }
               }else {
                   //enter if username is empty
                   tvRegisterState.setText("stupid Username empty");


               }



            }else {
                //enter if email is empty
                tvRegisterState.setText("stupid Email empty");
            }

        }else{
            // enter if lname is empty
            tvRegisterState.setText("stupid Last Name empty");
        }

    }else {
        //enter if fname is empty
        tvRegisterState.setText("stupid First Name empty");
    }



}

    private boolean CheckRegisterCredentials() {
    boolean result =false;


    return result;
    }


    private View.OnClickListener clickListener= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.AccRegister :
                    Register();
                    break;

            }
        }
    };
}

