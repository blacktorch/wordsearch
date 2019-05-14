package com.chidiebere.wordsearch;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.chidiebere.wordsearch.Constants.JAVA;
import static com.chidiebere.wordsearch.Constants.KOTLIN;
import static com.chidiebere.wordsearch.Constants.MOBILE;
import static com.chidiebere.wordsearch.Constants.OBJECTIVEC;
import static com.chidiebere.wordsearch.Constants.SWIFT;
import static com.chidiebere.wordsearch.Constants.VARIABLE;

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
        final List<String> foundWords = new ArrayList<>();

        final ResultTextView objectiveC = findViewById(R.id.text_objectivec);
        final ResultTextView java = findViewById(R.id.text_java);
        final ResultTextView swift = findViewById(R.id.text_swift);
        final ResultTextView kotlin = findViewById(R.id.text_kotlin);
        final ResultTextView mobile = findViewById(R.id.text_mobile);
        final ResultTextView variable = findViewById(R.id.text_variable);



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
                            float centreX = cellView.getX() + cellView.getWidth() / 2.0f;
                            float centreY = cellView.getY() + cellView.getHeight() / 2.0f;

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
                                    float cX = cv.getX() + cv.getWidth() / 2.0f;
                                    float cY = cv.getY() + cv.getHeight() / 2.0f;
                                    Log.i(">>>>><<<<<<<????????", buildWord.toString());

                                    for (String word : Constants.words){
                                        if (word.equals(buildWord.toString())){
                                            if (!foundWords.contains(buildWord.toString())){
                                                foundWords.add(buildWord.toString());
                                                gridView.setEndX(cX);
                                                gridView.setEndY(cY);
                                                gridView.draw();
                                                gridView.createLine();
                                                isWordCorrect[0] = true;

                                                String foundWord = buildWord.toString();

                                                switch (foundWord){
                                                    case JAVA:
                                                        java.setDeleted(true);
                                                        java.setPaint(gridView.getPaint());
                                                        java.startStrikeThroughAnimation();
                                                        buildWord.setLength(0);
                                                        break;
                                                    case SWIFT:
                                                        swift.setDeleted(true);
                                                        swift.setPaint(gridView.getPaint());
                                                        swift.startStrikeThroughAnimation();
                                                        buildWord.setLength(0);
                                                        break;
                                                    case KOTLIN:
                                                        kotlin.setDeleted(true);
                                                        kotlin.setPaint(gridView.getPaint());
                                                        kotlin.startStrikeThroughAnimation();
                                                        buildWord.setLength(0);
                                                        break;
                                                    case MOBILE:
                                                        mobile.setDeleted(true);
                                                        mobile.setPaint(gridView.getPaint());
                                                        mobile.startStrikeThroughAnimation();
                                                        buildWord.setLength(0);
                                                        break;
                                                    case VARIABLE:
                                                        variable.setDeleted(true);
                                                        variable.setPaint(gridView.getPaint());
                                                        variable.startStrikeThroughAnimation();
                                                        buildWord.setLength(0);
                                                        break;
                                                    case OBJECTIVEC:
                                                        objectiveC.setDeleted(true);
                                                        objectiveC.setPaint(gridView.getPaint());
                                                        objectiveC.startStrikeThroughAnimation();
                                                        buildWord.setLength(0);
                                                        break;
                                                }

                                                break;
                                            }
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
