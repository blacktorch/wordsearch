package com.chidiebere.wordsearch;

import android.graphics.Paint;

public class Line {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private Paint paint;

    public Line(float startX, float startY, float endX, float endY, Paint paint){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.paint = paint;

    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public Paint getPaint() {
        return paint;
    }
}
