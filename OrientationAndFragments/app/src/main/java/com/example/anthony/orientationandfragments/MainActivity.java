package com.example.anthony.orientationandfragments;

import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
    setContentView(R.layout.activity_main_landscape);
}
   else     if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main_portrait);
        }


    }


    public void alert(View view){

       Myalert m;
        m = new Myalert();
        m.show(getFragmentManager(),"default dialog");

    }
}
