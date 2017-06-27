package com.example.anthony.cameraproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by anthony on 6/27/17.
 */

public class SecondPage extends Activity implements View.OnClickListener,RadioGroup.OnCheckedChangeListener {
  RadioGroup rg;
    Button b ;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        rg= (RadioGroup)findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        b= (Button)findViewById(R.id.goBack);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==b.getId()){
            Intent i = new Intent() ;
            Bundle bundle = new Bundle();
            RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
            String s = rb.getText().toString();
            bundle.putString("key",s);
            i.putExtras(bundle);
            setResult(RESULT_OK,i);
            finish();

            finish();
        }



    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

    }
}
