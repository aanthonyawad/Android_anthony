package com.example.anthony.examplesharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by anthony on 6/20/17.
 */

public class secondact extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondpage);

    }





    public void method1(View view){
        EditText t = (EditText) findViewById(R.id.text);

        String s =""+t.getText() ;

        Switch swash= (Switch) findViewById(R.id.swash);

        SharedPreferences mypref= this.getSharedPreferences("mypref",Context.MODE_PRIVATE);

        SharedPreferences.Editor e= mypref.edit();

        e.putString("anthony", s);
        e.commit();

    }


    public void leave(View view){
     finish();
    }
}
