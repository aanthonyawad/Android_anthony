package com.example.anthony.canvasproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Collections;

/**
 * Created by anthony on 6/28/17.
 */

public class MyView extends View implements View.OnTouchListener {
    private float ClickYY,UnclickYY,ClickXX,UnclickXX;
    float currentXX,currentYY;
    float beforeX [];
    float beforeY [];
    SnakeStruc s1,s2,s3,s4;

    boolean isFirst,moving;
    // private final GestureDetector gestureDetector;
    boolean goleft,goright,goup,godown,clicking;
    Bitmap gBall;
    boolean bool;
    int changingY, changingX;
    public MyView(Context context) {
        super(context);

        s1= new SnakeStruc(0,0,null,s2);
        s2= new SnakeStruc(0,80,s1,s3);
        s3 = new SnakeStruc(0,160,s2,s4);
        s4 = new SnakeStruc(0,240,s3,null);

        beforeX =new float[3];
        beforeY = new float[3];
        moving=false;
        ClickYY=0;
        isFirst=true;
        UnclickYY=0;
        //   gestureDetector = new GestureDetector(context, new GestureListener());
     
        changingX=0;
        changingY=0;
        bool=true;
        goleft=false;
        goright=false;
        godown=false;
        goup=false;
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                onTouchEvent(motionEvent);
                return true;
            }
        });

//        this.setOnGenericMotionListener(new OnGenericMotionListener() {
//            @Override
//            public boolean onGenericMotion(View view, MotionEvent motionEvent) {
//               if(motionEvent==MotionEvent.)
//
//                return false;
//            }
//        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if(action==MotionEvent.ACTION_MOVE){
            currentXX=event.getX();
            currentYY=event.getY();




        }






        if (action == MotionEvent.ACTION_UP) {
            currentXX=event.getX();
            currentYY=event.getY();
            moving=false;
            // moving=false;

//            UnclickYY=event.getY();
//            UnclickXX=event.getX();
//            if(ClickYY-UnclickYY>200){
//                onSwipeTop();}
//            else
//                if(ClickYY-UnclickYY<-200){
//                onSwipeBottom();
//
//            }
//
//            if(ClickXX-UnclickXX>200){
//                onSwipeLeft();}
//            else
//                if(ClickXX-UnclickXX<-200){
//                onSwipeRight();
//
//            }
        }


        if (action == MotionEvent.ACTION_DOWN) {
            currentXX=event.getX();
            currentYY=event.getY();

            moving=true;

//
//
//            ClickYY=event.getY();
//                ClickXX=event.getX();
//
//        return true;
        }

        return true;
    }





    public void onSwipeRight() {
        goleft=false;
        goright=true;
        godown=false;
        goup=false;
    }

    public void onSwipeLeft() {
        goleft=true;
        goright=false;
        godown=false;
        goup=false;
    }

    public void onSwipeTop() {
        goleft=false;
        goright=false;
        godown=false;
        goup=true;

    }

    public void onSwipeBottom() {

        goleft=false;
        goright=false;
        godown=true;
        goup=false;
    }



    public void changePostion(float cx, float cy){
        s4.x=s3.x; s4.y=s3.y;
        s3.x=s2.x;s3.y=s2.y;
        s2.x=s1.x;s2.y=s1.y;
        s1.x=cx; s1.y=cy ;

    }



    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        if(isFirst){
            currentXX=canvas.getWidth()/2;
            currentYY=canvas.getHeight()/2;
            isFirst=false;


        }
        //set the background
        canvas.drawColor(Color.WHITE);




        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        canvas.drawText("PRESS AND HOLD SO THE SNAKE FOLLOWS YOU",50,50,paint);

        if(changingX<currentXX-canvas.getWidth()/2)changingX+=40;
        else if (changingX>currentXX-canvas.getWidth()/2)changingX-=40;

        if(changingY<currentYY-canvas.getHeight()/2)changingY+=40;
        else if(changingY>currentYY-canvas.getHeight()/2)changingY-=40;



        if(moving) {

            changePostion(changingX, changingY);

            System.out.println("s1x "+s1.x+"s1y"+s1.y);

            System.out.println("s2x "+s2.x+"s2y"+s2.y);

            System.out.println("s3x "+s3.x+"s3y"+s3.y);

            System.out.println("s4x "+s4.x+"s4y"+s4.y);
        }
        canvas.drawCircle(s1.x+canvas.getWidth()/2,s1.y+canvas.getHeight()/2,40,paint);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        canvas.drawCircle(s2.x+canvas.getWidth()/2,s2.y+canvas.getHeight()/2,40,paint);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canvas.drawCircle(s3.x+canvas.getWidth()/2,s3.y+canvas.getHeight()/2,40,paint);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canvas.drawCircle(s4.x+canvas.getWidth()/2,s4.y+canvas.getHeight()/2,40,paint);
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



//        if(goup){ if(!(changingY<-canvas.getHeight()/2))changingY-=5;}
//        if(goleft){if(!(changingX<-canvas.getWidth()/2))changingX-=5;}
//        if(goright){if(!(changingX>canvas.getWidth()/2))changingX+=5;}
//        if(godown){if(!(changingY>canvas.getHeight()/2))changingY+=5;}


        // changing the ball on every draw
//        if(changingY<canvas.getHeight()-120)
//        {changingY+=5;
//           /// System.out.println("YYYYYYYYYY"+changingY);
//        }
//        else{
//            bool=false;changingY=0;}
//



        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
