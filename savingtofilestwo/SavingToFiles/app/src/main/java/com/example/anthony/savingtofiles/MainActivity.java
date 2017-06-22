package com.example.anthony.savingtofiles;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    RadioButton r1;
    RadioButton r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1 =(RadioButton)findViewById(R.id.rb1);
        r2  =(RadioButton)findViewById(R.id.rb2);
    //End of onCreate
    }


    public void saveToFile(View view){
        String string = "Nothing  was ever checked";
        if(r1.isChecked()){
            string = "r1 was chekced last time";

        }
        if(r2.isChecked()){
            string = "r2 was chekced last time";
        }

        String filename = "f3";

        FileOutputStream outputStream;

        try {
 outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
 outputStream.write(string.getBytes());
 outputStream.close();
        } catch (Exception e) {
 e.printStackTrace();
        }

// end of method saveToFile
    }

    public void appendToFile(View view){
        String string = "Nothing  was ever checked";
        if(r1.isChecked()){
            string = "r1 was chekced last time";

        }
        if(r2.isChecked()){
            string = "r2 was chekced last time";
        }

        String filename = "f3";

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

// end of method appendToFile
    }


    public void readFile(View view){

        String filename = "f3";

        FileInputStream inputStream;
        String output ="";
        try {

            inputStream = openFileInput(filename);


            InputStreamReader isr = new InputStreamReader ( inputStream ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
               output += readString;
                readString = buffreader.readLine ( ) ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(),output,Toast.LENGTH_LONG).show();

        //end method ReadFile
    }


    public void deleteFile(View view){
//        String string = "";
//
//
        String filename = "f3";
//
//        FileOutputStream outputStream;
//
//        try {
//            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
//            outputStream.write(string.getBytes());
//            outputStream.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }




        File dir = getFilesDir();
        File file = new File(dir, filename);
        boolean deleted = file.delete();


// end of method saveToFile
    }
    public void goAct2(View view){
        Intent i = new Intent(MainActivity.this,second_activity.class);
        startActivity(i);


    }




    //end of class
}
