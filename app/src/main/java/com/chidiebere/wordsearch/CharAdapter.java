package com.chidiebere.wordsearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


public class CharAdapter extends BaseAdapter {

    private final Context context;
    private char[][] letters;


    private List<Character> characters;

    public CharAdapter(Context context){
        this.context = context;

        WordGenerator wordGenerator = new WordGenerator();

        letters = wordGenerator.generateRandomWords();

        characters = convertMultiDimeToList(letters);
    }

    private List<Character> convertMultiDimeToList(char[][] multiArr){
        List<Character> list = new ArrayList<>();
        for (char[] row : multiArr) {
            for (int col = 0; col < multiArr.length; col++) {
                list.add(row[col]);
            }
        }

        return list;
    }

    @Override
    public int getCount() {
        return characters.size();
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
        charView.setText(String.valueOf(characters.get(position).charValue()));
        //Log.d("POSITION", "getView: " + position);
        return convertView;
    }
}
