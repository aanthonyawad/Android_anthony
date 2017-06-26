package com.example.anthony.listmenu;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by anthony on 6/23/17.
 */

public class PageTwo extends Activity {
    String name;
    MediaPlayer mySong ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        Intent i = getIntent();
        name =  i.getStringExtra("name");
       if(name.equals("person1")) {
           TextView tv = (TextView) findViewById(R.id.tv);
           tv.setText("Person1 message");
           mySong = MediaPlayer.create(PageTwo.this,R.raw.music2);

       }else if(name.equals("person2")) {
           TextView tv = (TextView) findViewById(R.id.tv);
           tv.setText("Person2 message");
           mySong = MediaPlayer.create(PageTwo.this,R.raw.music1);

       }else if(name.equals("person3")) {
           TextView tv = (TextView) findViewById(R.id.tv);
           tv.setText("Person3 message");
           mySong = MediaPlayer.create(PageTwo.this,R.raw.music1);

       }



        mySong.start();
    }

public void play(View view){
    if(mySong.isPlaying()){
        //no need to do anything geniye meshye
    }
    else
    mySong.start();
}


public void pause(View view){
    if(mySong.isPlaying())
  mySong.pause();
}

public void replay(View view){

    mySong.stop();
    try{mySong.prepare();}
    catch (Exception e){
        System.out.println("fetet bel error");
        e.printStackTrace();}
    mySong.start();

}

// to stop the song when i leave the activity :3
    @Override protected void onDestroy() {
        super.onDestroy();

        if (mySong != null) {
            mySong.stop();

        }
    }



}
