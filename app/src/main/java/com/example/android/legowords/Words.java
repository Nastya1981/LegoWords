package com.example.android.legowords;

import android.widget.Button;

public class Words {
    public String[] mWord;
    public String[] mImage;
    Words(String[] Word, String[] Image){
        this.mWord = Word;
        this.mImage = Image;
    }

   /* private void fillWordsMap() {
        String[] strings = getResources().getStringArray(R.array.words);
        for (String string: strings){
            Log.e("Words", string);
        }
    }*/
}
