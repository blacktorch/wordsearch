package com.chidiebere.wordsearch;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;

import java.io.Serializable;

/***
 * A custom TextView with Strike Through animation abilities
 */

public class ResultTextView extends android.support.v7.widget.AppCompatTextView implements Serializable {

    private static final float LINE_WIDTH = 50.0f;
    private GridPaint paint;
    private boolean isAnimationStarted;
    private float targetLength;
    private float totalLength;
    private float startY;

    //should always show Strike-Through
    private boolean isDeleted;
    private boolean isResume;

    public ResultTextView(Context context) {
        super(context);
        paint = new GridPaint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Palette.colors[2]);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(90);
    }

    public ResultTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new GridPaint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(Palette.colors[2]);
        paint.setStrokeWidth(LINE_WIDTH);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setDither(true);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(90);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isAnimationStarted) {
            canvas.drawLine(25, startY, targetLength, startY, paint);
        }
        if (isDeleted && !isAnimationStarted) {
            canvas.drawLine(25, startY, totalLength, startY, paint);
        }

        if (isResume){
            canvas.drawLine(25, startY, totalLength, startY, paint);
        }

    }

    public void startStrikeThroughAnimation() {
        totalLength = getWidth()-25;
        startY = (float) getHeight() / 2;
        isAnimationStarted = true;

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "targetLength", 25, totalLength);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isAnimationStarted = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        objectAnimator.setDuration(400);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        objectAnimator.start();
        postInvalidate();
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
        totalLength = getWidth()-25;
        isAnimationStarted = false;
        isResume = false;

    }

    public float getTargetLength() {
        return targetLength;
    }

    public void setTargetLength(float targetLength) {
        this.targetLength = targetLength;
    }

    public void setPaint(GridPaint paint){
        this.paint = paint;
    }
    public GridPaint getGridPaint(){
        return paint;
    }

    public void reDrawLine(){
        isResume = true;
        totalLength = getWidth()-25;
    }


}
