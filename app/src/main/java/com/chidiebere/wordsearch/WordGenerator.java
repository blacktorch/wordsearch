package com.chidiebere.wordsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.chidiebere.wordsearch.Constants.HORIZONTAL;
import static com.chidiebere.wordsearch.Constants.JAVA;
import static com.chidiebere.wordsearch.Constants.KOTLIN;
import static com.chidiebere.wordsearch.Constants.MOBILE;
import static com.chidiebere.wordsearch.Constants.OBJECTIVEC;
import static com.chidiebere.wordsearch.Constants.RIGHT;
import static com.chidiebere.wordsearch.Constants.SWIFT;
import static com.chidiebere.wordsearch.Constants.VARIABLE;
import static com.chidiebere.wordsearch.Constants.VERTICAL;
import static com.chidiebere.wordsearch.Constants.alphabets;

/***
 * A Class that helps generate the content of the grid,
 * it also randomizes the positions of the words
 */

public class WordGenerator {

    private char [][] letters = new char[10][10];

    private Map<String, ArrayList<Position>> words = new HashMap<>();

    private ArrayList<Position> usedPositions = new ArrayList<>();

    public WordGenerator(){

        Random rand = new Random(System.currentTimeMillis());

        for(int row = 0; row < letters.length; row++){
            for(int col = 0; col < letters.length; col++){
                letters[row][col] = alphabets[rand.nextInt(26)];
            }
        }


    }

    private void randomize(char[] word, Position position){

        int[] pos = new int[1];
        int[] row = {position.getStartRow()};
        int[] col = {position.getStartCol()};

        if (position.getOrientation() == HORIZONTAL){
            pos = col;
        } else if (position.getOrientation() == VERTICAL){
            pos = row;
        }

        if (position.getDirection() == RIGHT){
            for (char l : word) {
                letters[row[0]][col[0]] = l;
                pos[0]++;
            }
        } else{

            for(int i = word.length-1; i >= 0; i--){

                letters[row[0]][col[0]] = word[i];
                pos[0]++;
            }
        }

    }

    private Map<String, ArrayList<Position>> generateWords(){
        Map<String, ArrayList<Position>> words = new HashMap<>();
        Random rand = new Random(System.currentTimeMillis());
        words.put(JAVA, new ArrayList<Position>());
        words.put(SWIFT, new ArrayList<Position>());
        words.put(KOTLIN, new ArrayList<Position>());
        words.put(MOBILE, new ArrayList<Position>());
        words.put(VARIABLE, new ArrayList<Position>());
        words.put(OBJECTIVEC,new ArrayList<Position>());

        words.get(JAVA).add(new Position(HORIZONTAL, rand.nextInt(2),0,0,0,3));
        words.get(JAVA).add(new Position(HORIZONTAL, rand.nextInt(2),2,2,1,4));
        words.get(JAVA).add(new Position(VERTICAL, rand.nextInt(2),4,7,0,0));
        words.get(JAVA).add(new Position(VERTICAL, rand.nextInt(2),4,7,1,1));

        words.get(SWIFT).add(new Position(HORIZONTAL,rand.nextInt(2),3,3,0,4));
        words.get(SWIFT).add(new Position(HORIZONTAL,rand.nextInt(2),1,1,0,4));
        words.get(SWIFT).add(new Position(VERTICAL,rand.nextInt(2),2,6,7,7));

        words.get(KOTLIN).add(new Position(HORIZONTAL, rand.nextInt(2), 8,8,2,7));
        words.get(KOTLIN).add(new Position(VERTICAL, rand.nextInt(2), 1,6,5,5));

        words.get(MOBILE).add(new Position(VERTICAL, rand.nextInt(2), 1,6,6,6));
        words.get(MOBILE).add(new Position(HORIZONTAL, rand.nextInt(2), 7,7,2,7));

        words.get(VARIABLE).add(new Position(VERTICAL, rand.nextInt(2), 1,8,8,8));
        words.get(VARIABLE).add(new Position(HORIZONTAL, rand.nextInt(2), 9,9,0,7));

        words.get(OBJECTIVEC).add(new Position(VERTICAL, rand.nextInt(2), 0,9,9,9));

        return words;
    }

    private boolean isUsedPosition(Position position, ArrayList<Position> used){
        for (Position pos : used) {
            if (pos.getOrientation() == position.getOrientation() &&
                    pos.getStartCol() == position.getStartCol() &&
                    pos.getEndCol() == position.getEndCol() &&
                    pos.getStartRow() == position.getStartRow() &&
                    pos.getEndRow() == position.getEndRow()){
                return true;
            }
        }

        return false;
    }

    public char[][] generateRandomWords(){
        Random rand = new Random(System.currentTimeMillis());

        words = generateWords();

        ArrayList<Position> positionSelect;
        Position position;

        //Java Randomization
        positionSelect =  words.get(JAVA);
        position = positionSelect.get(rand.nextInt(positionSelect.size()));
        randomize(JAVA.toCharArray(), position);
        usedPositions.add(position);

        //Swift Randomization
        positionSelect =  words.get(SWIFT);
        position = positionSelect.get(rand.nextInt(positionSelect.size()));
        randomize(SWIFT.toCharArray(), position);
        usedPositions.add(position);

        //Mobile Randomization
        positionSelect =  words.get(MOBILE);
        position = positionSelect.get(rand.nextInt(positionSelect.size()));
        randomize(MOBILE.toCharArray(), position);
        usedPositions.add(position);

        //Maximize available positions for Kotlin
        if (!isUsedPosition(new Position(VERTICAL,0,1,6, 6,6),usedPositions)){
            words.get(KOTLIN).add(new Position(VERTICAL,rand.nextInt(2),1,6, 6,6));
        }

        if (!isUsedPosition(new Position(VERTICAL,0,2,6, 7,7),usedPositions)){
            words.get(KOTLIN).add(new Position(VERTICAL,rand.nextInt(2),1,6, 7,7));
        }

        //Kotlin Randomization
        positionSelect =  words.get(KOTLIN);
        position = positionSelect.get(rand.nextInt(positionSelect.size()));
        randomize(KOTLIN.toCharArray(), position);
        usedPositions.add(position);

        //Maximize available positions for Variable
        if (!isUsedPosition(new Position(HORIZONTAL,0, 8,8,2,7), usedPositions)){
            words.get(VARIABLE).add(new Position(HORIZONTAL, rand.nextInt(2), 9,9,0,7));
        }

        //Variable Randomization
        positionSelect =  words.get(VARIABLE);
        position = positionSelect.get(rand.nextInt(positionSelect.size()));
        randomize(VARIABLE.toCharArray(), position);
        usedPositions.add(position);

        //Maximize available positions for ObjectiveC
        if (!isUsedPosition(new Position(HORIZONTAL,0, 0,0,0,3), usedPositions)){
            words.get(OBJECTIVEC).add(new Position(HORIZONTAL, rand.nextInt(2), 0,0,0,9));
        }
        if (!isUsedPosition(new Position(VERTICAL,0, 1,8,8,8), usedPositions)){
            words.get(OBJECTIVEC).add(new Position(VERTICAL, rand.nextInt(2), 0,9,8,8));
        }
        if (!isUsedPosition(new Position(HORIZONTAL,0, 9,9,0,7), usedPositions)){
            words.get(OBJECTIVEC).add(new Position(HORIZONTAL, rand.nextInt(2), 9,9,0,9));
        }

        //ObjectiveC Randomization
        positionSelect =  words.get(OBJECTIVEC);
        position = positionSelect.get(rand.nextInt(positionSelect.size()));
        randomize(OBJECTIVEC.toCharArray(), position);
        usedPositions.add(position);

        return letters;
    }
}
