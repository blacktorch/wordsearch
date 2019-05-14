package com.chidiebere.wordsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CharAdapter extends BaseAdapter implements Serializable {

    private final Context context;
    private char[][] letters;


    private char[] characters;

    public CharAdapter(Context context){
        this.context = context;

        WordGenerator wordGenerator = new WordGenerator();

        letters = wordGenerator.generateRandomWords();

        characters = convertMultiDimeToList(letters);
    }

    private char[] convertMultiDimeToList(char[][] multiArr){
        List<Character> list = new ArrayList<>();
        char[] charList = new char[100];
        for (char[] row : multiArr) {
            for (int col = 0; col < multiArr.length; col++) {
                list.add(row[col]);
            }
        }

        for(int i = 0; i < list.size(); i++){
            charList[i] = list.get(i);
        }

        return charList;
    }

    @Override
    public int getCount() {
        return characters.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            final LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.character_layout,parent,false);


        }
        TextView charView = convertView.findViewById(R.id.letter);
        charView.setText(String.valueOf(characters[position]));
        //Log.d("POSITION", "getView: " + position);
        return convertView;
    }

    public char[] getCharacters(){
        return characters;
    }

    public void setCharacters(char[] characters){
        this.characters = characters;
    }
}
