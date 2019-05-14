package com.chidiebere.wordsearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.chidiebere.wordsearch.Constants.JAVA;
import static com.chidiebere.wordsearch.Constants.KOTLIN;
import static com.chidiebere.wordsearch.Constants.MOBILE;
import static com.chidiebere.wordsearch.Constants.OBJECTIVEC;
import static com.chidiebere.wordsearch.Constants.SWIFT;
import static com.chidiebere.wordsearch.Constants.VARIABLE;

public class MainActivity extends AppCompatActivity implements Serializable {


    List<String> foundWords;

    ResultTextView objectiveC;
    ResultTextView java;
    ResultTextView swift;
    ResultTextView kotlin;
    ResultTextView mobile;
    ResultTextView variable;

    ResultTextView rObjectiveC;
    ResultTextView rJava;
    ResultTextView rSwift;
    ResultTextView rKotlin;
    ResultTextView rMobile;
    ResultTextView rVariable;

    Animation textOut;
    Animation scaleUp;
    Animation scaleDown;

    TextView wordCount;
    int wordsLeftCount = 6;

    char[] characters;

    ArrayList<Line> lines;
    CharAdapter charAdapter;
    boolean isResume = false;
    long pausedTime = 0;
    boolean isComplete = false;
    boolean isRefresh = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final boolean[] isWordCorrect = {false};
        final int[] currentPosition = {-1};
        final StringBuilder buildWord = new StringBuilder();

        objectiveC = findViewById(R.id.text_objectivec);
        java = findViewById(R.id.text_java);
        swift = findViewById(R.id.text_swift);
        kotlin = findViewById(R.id.text_kotlin);
        mobile = findViewById(R.id.text_mobile);
        variable = findViewById(R.id.text_variable);

        final TextView formedText = findViewById(R.id.formed_text);
        final Animation textIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_in);
        textOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.text_out);
        scaleUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_down);
        foundWords = new ArrayList<>();

        wordCount = findViewById(R.id.word_count);

        final long[] elapsedTime = new long[1];
        final Chronometer timer = findViewById(R.id.timer_text);

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                elapsedTime[0] = SystemClock.elapsedRealtime();

            }
        });

        charAdapter = new CharAdapter(this);
         final MainView gridView = findViewById(R.id.main_view);

        gridView.setAdapter(charAdapter);



        //Handle App State Restore
        if (savedInstanceState != null){
            boolean resume = savedInstanceState.getBoolean("RESUME");
            if (resume) {
                isResume = true;
                foundWords = savedInstanceState.getStringArrayList("FOUND_WORDS");
                rObjectiveC = (ResultTextView) savedInstanceState.getSerializable(OBJECTIVEC);
                rJava = (ResultTextView) savedInstanceState.getSerializable(JAVA);
                rSwift = (ResultTextView) savedInstanceState.getSerializable(SWIFT);
                rKotlin = (ResultTextView) savedInstanceState.getSerializable(KOTLIN);
                rMobile = (ResultTextView) savedInstanceState.getSerializable(MOBILE);
                rVariable = (ResultTextView) savedInstanceState.getSerializable(VARIABLE);
                pausedTime = savedInstanceState.getLong("TIME");
                wordsLeftCount = savedInstanceState.getInt("COUNT");
                characters = savedInstanceState.getCharArray("CHAR");
                isComplete = savedInstanceState.getBoolean("COMPLETE");
                lines = savedInstanceState.getParcelableArrayList("LINE");
            } else {
                isResume = false;
            }
        }

        if (isResume && !isRefresh) {
            if (lines != null){
                gridView.setLines(lines);
            }
            timer.setBase(pausedTime);
            Log.i("###########", "TIME: " + String.valueOf(pausedTime));
            timer.start();
            wordCount.setText(String.valueOf(wordsLeftCount));
            charAdapter.setCharacters(characters);
            charAdapter.notifyDataSetChanged();
            if (isComplete){
                formedText.setText("COMPLETED!");
                formedText.startAnimation(textIn);
                timer.stop();
                gridView.setEnabled(false);
            }

        } else {
            characters = charAdapter.getCharacters();
            timer.start();
        }


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

                            if (position != currentPosition[0]) {
                                buildWord.append(a);
                                formedText.setText(buildWord.toString());
                                if (formedText.getVisibility() == View.INVISIBLE) {
                                    formedText.startAnimation(textIn);
                                    formedText.setVisibility(View.VISIBLE);
                                }
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
                                    if (gridView.getStartX() == 0 && gridView.getStartY() == 0) {
                                        gridView.setStartY(event.getY());
                                        gridView.setStartX(event.getX());
                                    }
                                    gridView.setEndX(event.getX());
                                    gridView.setEndY(event.getY());
                                    gridView.draw();
                                    break;
                                case MotionEvent.ACTION_UP:
                                    handleEndSwipe(position, gridView, buildWord, formedText, isWordCorrect, timer, textIn);

                                    break;
                            }

                        } else {
                            if (action == MotionEvent.ACTION_UP) {
                                buildWord.setLength(0);
                                gridView.clear();
                                formedText.setText("");
                                formedText.setVisibility(View.INVISIBLE);
                            }
                        }

                        break;

                }

                return true;
            }


        });

    }

    private void handleEndSwipe(int position, final MainView gridView, StringBuilder buildWord, final TextView formedText, boolean[] isWordCorrect, final Chronometer timer, final Animation textIn) {
        TextView cv = (TextView) gridView.getChildAt(position);
        float cX = cv.getX() + cv.getWidth() / 2.0f;
        float cY = cv.getY() + cv.getHeight() / 2.0f;
        Log.i(">>>>><<<<<<<????????", buildWord.toString());

        for (String word : Constants.words) {
            if (word.equals(buildWord.toString())) {
                if (!foundWords.contains(buildWord.toString())) {
                    foundWords.add(buildWord.toString());
                    gridView.setEndX(cX);
                    gridView.setEndY(cY);
                    gridView.draw();
                    lines = gridView.createLine();
                    isWordCorrect[0] = true;

                    String foundWord = buildWord.toString();

                    switch (foundWord) {
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

                    wordsLeftCount--;

                    vibrate();
                    formedText.startAnimation(scaleUp);
                    formedText.startAnimation(scaleDown);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            wordCount.setText(String.valueOf(wordsLeftCount));
                            if (wordsLeftCount == 0) {
                                isComplete = true;
                                formedText.setText("COMPLETED!");
                                formedText.startAnimation(textIn);
                                timer.stop();
                                gridView.setEnabled(false);
                            } else {
                                formedText.startAnimation(textOut);
                                formedText.setVisibility(View.INVISIBLE);
                            }
                        }
                    }, 1000);

                    break;
                }
            }
        }
        buildWord.setLength(0);
        if (!isWordCorrect[0]) {
            gridView.clear();
            formedText.setText("");
            formedText.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        try {
            if (!isRefresh){
                outState.putStringArrayList("FOUND_WORDS", (ArrayList<String>) foundWords);
                outState.putSerializable(JAVA, java);
                outState.putSerializable(SWIFT, swift);
                outState.putSerializable(KOTLIN, kotlin);
                outState.putSerializable(MOBILE, mobile);
                outState.putSerializable(VARIABLE, variable);
                outState.putSerializable(OBJECTIVEC, objectiveC);
                outState.putParcelableArrayList("LINE", lines);
                outState.putCharArray("CHAR", characters);
                outState.putInt("COUNT", wordsLeftCount);
                outState.putLong("TIME", SystemClock.elapsedRealtime());
                outState.putBoolean("COMPLETE", isComplete);
                outState.putBoolean("RESUME", true);
                super.onSaveInstanceState(outState);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.refresh, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            recreate();
            isRefresh = true;
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isRefresh){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reDrawStrikeThrough();
                }
            }, 500);
        }
        isRefresh = false;
    }

    private void reDrawStrikeThrough(){
        if (foundWords != null){
            for (String word : foundWords){
                switch (word){
                    case JAVA:
                        java.setDeleted(true);
                        java.setPaint(rJava.getGridPaint());
                        java.startStrikeThroughAnimation();
                        break;
                    case SWIFT:
                        swift.setDeleted(true);
                        swift.setPaint(rSwift.getGridPaint());
                        swift.startStrikeThroughAnimation();
                        break;
                    case KOTLIN:
                        kotlin.setDeleted(true);
                        kotlin.setPaint(rKotlin.getGridPaint());
                        kotlin.startStrikeThroughAnimation();
                        break;
                    case MOBILE:
                        mobile.setDeleted(true);
                        mobile.setPaint(rMobile.getGridPaint());
                        mobile.startStrikeThroughAnimation();
                        break;
                    case VARIABLE:
                        variable.setDeleted(true);
                        variable.setPaint(rVariable.getGridPaint());
                        variable.startStrikeThroughAnimation();
                        break;
                    case OBJECTIVEC:
                        objectiveC.setDeleted(true);
                        objectiveC.setPaint(rObjectiveC.getGridPaint());
                        objectiveC.startStrikeThroughAnimation();
                        break;
                }
            }
        }
    }

    private void vibrate(){
        // Get instance of Vibrator from current Context
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Start without a delay
        // Each element then alternates between vibrate, sleep, vibrate, sleep...
        long[] pattern = {0, 80, 100, 80, 100};

        // The '-1' here means to vibrate once, as '-1' is out of bounds in the pattern array
        v.vibrate(pattern, -1);
    }
}


