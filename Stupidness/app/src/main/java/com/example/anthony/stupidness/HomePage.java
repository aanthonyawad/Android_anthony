package com.example.anthony.stupidness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by anthony on 7/7/17.
 */

public class HomePage extends AppCompatActivity {
    TextView tvName,tvEmail;
    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        initialise();

    }


private void initialise(){
    i= getIntent();
    tvName= (TextView)findViewById(R.id.tvUserName);
    tvName.setText(i.getStringExtra("name"));
    tvEmail=(TextView)findViewById(R.id.tvUserEmail);
    tvEmail.setText(i.getStringExtra("email"));

}
}
