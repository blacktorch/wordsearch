package com.chidiebere.wordsearch;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainView gridView = findViewById(R.id.main_view);
        final CharAdapter charAdapter = new CharAdapter(this);
        gridView.setAdapter(charAdapter);


        final boolean[] isWordCorrect = {false};
        final int[] currentPosition = {-1};
        final StringBuilder buildWord = new StringBuilder();


        gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getActionMasked();
                isWordCorrect[0] = false;

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:

                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        int position = gridView.pointToPosition(x, y);

                        if (position != GridView.INVALID_POSITION) {

                            v.getParent().requestDisallowInterceptTouchEvent(true);
                            TextView cellView = (TextView) gridView.getChildAt(position);
                            float centreX = cellView.getX() + cellView.getWidth() / 2;
                            float centreY = cellView.getY() + cellView.getHeight() / 2;

                            String a = cellView.getText().toString();

                            if (position != currentPosition[0]){
                                buildWord.append(a);
                                currentPosition[0] = position;
                            }


                            switch (action) {
                                case MotionEvent.ACTION_DOWN:
                                    gridView.randomizePaintColor();
                                    gridView.setStartX(centreX);
                                    gridView.setStartY(centreY);

                                    gridView.setEndX(centreX);
                                    gridView.setEndY(centreY);
                                    gridView.draw();
                                    break;

                                case MotionEvent.ACTION_MOVE:
                                    if (gridView.getStartX() == 0 && gridView.getStartY() == 0){
                                        gridView.setStartY(event.getY());
                                        gridView.setStartX(event.getX());
                                    }
                                    gridView.setEndX(event.getX());
                                    gridView.setEndY(event.getY());
                                    gridView.draw();
                                    break;
                                case MotionEvent.ACTION_UP:

                                    TextView cv = (TextView) gridView.getChildAt(position);
                                    float cX = cv.getX() + cv.getWidth() / 2;
                                    float cY = cv.getY() + cv.getHeight() / 2;
                                    Log.i(">>>>><<<<<<<????????", buildWord.toString());

                                    for (String word : Constants.words){
                                        if (word.equals(buildWord.toString())){
                                            gridView.setEndX(cX);
                                            gridView.setEndY(cY);
                                            gridView.draw();
                                            gridView.createLine();
                                            buildWord.setLength(0);
                                            isWordCorrect[0] = true;
                                            break;
                                        }
                                    }
                                    buildWord.setLength(0);
                                    if (!isWordCorrect[0]){
                                        gridView.clear();
                                    }

                                    break;
                            }

                        } else {
                            if (action == MotionEvent.ACTION_UP){
                                buildWord.setLength(0);
                                gridView.clear();
                            }
                        }

                        break;

                }

                return true;
            }


        });

    }
}
