package com.chidiebere.wordsearch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Random;

/***
 * A custom GridView with added functionality to Swipe and locate cell elements.
 */

public class MainView extends GridView {

    private static final float LINE_WIDTH = 50.0f;
    private GridPaint paint;
    protected Context context;
    Random rand;

    ArrayList<Line> lines;

    private float startX;
    private float startY;
    private float endX;
    private float endY;

    public MainView(Context context) {
        super(context);
        this.context = context;
        rand = new Random(System.currentTimeMillis());
        paint = new GridPaint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Palette.colors[rand.nextInt(Palette.colors.length)]);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(90);
        lines = new ArrayList<>();

    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        rand = new Random(System.currentTimeMillis());
        paint = new GridPaint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Palette.colors[rand.nextInt(Palette.colors.length)]);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(90);
        lines = new ArrayList<>();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, startY, endX, endY, paint);
        for(Line line : lines){
            canvas.drawLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY(), line.getPaint());
        }
    }


    public void setStartX(float startX) {
        this.startX = startX;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public void setEndY(float endY) {
        this.endY = endY;
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

    public void draw(){
        invalidate();
    }


    public void clear(){
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
        invalidate();
    }

    public void randomizePaintColor(){
        paint.setColor(Palette.colors[rand.nextInt(Palette.colors.length)]);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(90);

    }

    public ArrayList<Line> createLine(){
        lines.add(new Line(startX, startY, endX, endY, new GridPaint(paint)));
        return lines;
    }

    public GridPaint getPaint(){
        return paint;
    }

    public void setLines(ArrayList<Line> lines){
        this.lines = lines;
        invalidate();
    }
}
