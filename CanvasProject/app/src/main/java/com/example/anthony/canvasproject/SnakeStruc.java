package com.example.anthony.canvasproject;

/**
 * Created by anthony on 6/30/17.
 */

public class SnakeStruc {
    float x,y;
    SnakeStruc after,before;
    public SnakeStruc(float x, float y,SnakeStruc after ,SnakeStruc before){
        this.x=x;this.y=y;this.before=before;this.after=after;
    }
}
