package com.example.anthony.cameraproject;

import android.app.Activity;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends Activity implements View.OnClickListener{
    Bitmap bmp ;
    Intent i;
ImageButton ib;
    Button b;
    ImageView iv;
    Button b1;
    TextView tv;
   final static int cameraData=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);
        InputStream is = getResources().openRawResource(R.drawable.lebanon);
        bmp= BitmapFactory.decodeStream(is);
        ib=(ImageButton)findViewById(R.id.ibut);
        ib.setOnClickListener(this);
        b= (Button)findViewById(R.id.but);
        b.setOnClickListener(this);
        b1= (Button)findViewById(R.id.bAct);
        b1.setOnClickListener(this);
        iv=(ImageView)findViewById(R.id.iv);
        iv.setOnClickListener(this);
        tv=(TextView)findViewById(R.id.textView);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){

            case R.id.but :
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ibut :
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,cameraData);
                break;

            case R.id.iv :

                break;
            case R.id.bAct:
                Intent i = new Intent(MainActivity.this,SecondPage.class);
                startActivityForResult(i,4088);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==4088){
            // if i give intent start activity for result hayda requestcode li baatiya yeh
            if(resultCode==RESULT_OK) {
                System.out.println("fet b taba3 zero ");
                Bundle bzez = data.getExtras();
                String s = bzez.getString("key");
                tv.setText(s);
            }
        }else
            if(requestCode==0){
        if(resultCode == RESULT_OK){
            System.out.println("fet b taba3 else ");
            Bundle extra = data.getExtras();
            bmp = (Bitmap) extra.get("data");
            iv.setImageBitmap(bmp);
        }
        }
    }
}
