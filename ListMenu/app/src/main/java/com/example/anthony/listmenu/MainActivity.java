package com.example.anthony.listmenu;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

    }

    public void method(View view){
        Button b = (Button)view;
        String a=""+ b.getText();
        Intent i = new Intent(MainActivity.this, PageTwo.class);
        i.putExtra("name",a);
        startActivity(i);

    }



}
