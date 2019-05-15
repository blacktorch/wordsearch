package com.chidiebere.wordsearch;

import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;

/***
 * The Line shown while swiping on the grid
 */

public class Line implements Parcelable {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private GridPaint paint;

    public Line(float startX, float startY, float endX, float endY, GridPaint paint){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.paint = paint;

    }

    private Line(Parcel in){
        startX = in.readFloat();
        startY = in.readFloat();
        endX = in.readFloat();
        endY = in.readFloat();
        paint = in.readParcelable(new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        });
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeFloat(startX);
        dest.writeFloat(startY);
        dest.writeFloat(endX);
        dest.writeFloat(endY);
//        dest.writeParcelable((Parcelable) paint, flags);

    }

    public static final Parcelable.Creator<Line> CREATOR = new Parcelable.Creator<Line>() {
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        public Line[] newArray(int size) {
            return new Line[size];
        }
    };
}
