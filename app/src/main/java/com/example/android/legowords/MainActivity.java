package com.example.android.legowords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LinearLayout mLinearLayoutLetters;
    private LinearLayout mLinearLayoutForLetters;
    private ImageView mImageView;
    private TextView[] mLetters;

    private int mLevel = 0;
    private int mcountLetter = 0;
    private String[] mArrayWords;
    private String[] mArrayImages;

    String printWord = "";
    String mWord;
    int mWordLength;
    char[] chars;
    Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayoutLetters = (LinearLayout) findViewById(R.id.layout_letters);
        mLinearLayoutForLetters = (LinearLayout) findViewById(R.id.layout_textview);
        mImageView = (ImageView)findViewById(R.id.image_word);
        mArrayWords = getResources().getStringArray(R.array.words);
        mArrayImages = getResources().getStringArray(R.array.images);
        LegoWord();
    }
    public void LegoWord(){
        init();
        setImageView(mArrayImages[mLevel]);
        List <Character> shuffleLetters = shuffleLetters();
        createLetters(shuffleLetters);
        createContainerForLetter();
    }
    View.OnClickListener oclBtnLetter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String letter = Character.toString(chars[printWord.length()]);
            Button button = (Button) v;
            String buttonLetter = button.getText().toString();
            if (letter.equals(buttonLetter)) {
                mLetters[mcountLetter].setText(button.getText().toString());
                mcountLetter++;
                printWord += button.getText().toString();
                if (printWord.length() == mWord.length()){
                    if(mLevel != 3) {
                        mLevel++;
                        mLinearLayoutLetters.removeView(button);
                        mLinearLayoutForLetters.removeAllViews();
                        LegoWord();
                        // mTextView.addView(buttonOk);
                    }
                }
                mLinearLayoutLetters.removeView(button);
            }
        }
    };
    public void init(){
        mWord = mArrayWords[mLevel];
        mWordLength = mWord.length();
        chars = mWord.toCharArray();
        mcountLetter = 0;
        printWord= "";
    }
    public void setImageView(String image){
        mImageView.setImageResource(this
                .getResources()
                .getIdentifier(image, "drawable", this
                        .getPackageName()));
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
    public void createLetters(List Letters){
        buttons = new Button[mWordLength];
        for (int i = 0; i < mWordLength; i++) {
            buttons[i] = new Button(this);
         /* buttons[i].setTextSize(65);
            buttons[i].setWidth(100);
            buttons[i].setHeight(100);*/
            buttons[i].setText(String.valueOf(Letters.get(i)));
            buttons[i].setOnClickListener(oclBtnLetter);
            mLinearLayoutLetters.addView(buttons[i]);
        }
    }
    public void createContainerForLetter(){
        mLetters = new TextView[mWordLength];
        for (int i = 0; i < mWordLength; i++) {
            mLetters[i] = new TextView(this);
            mLetters[i].setBackgroundResource(R.drawable.roundrect);
            mLetters[i].setTextSize(55);
            mLetters[i].setWidth(100);
            mLetters[i].setHeight(100);
            mLinearLayoutForLetters.addView(mLetters[i]);
        }
    }
    public List shuffleLetters(){
        List<Character> Letters = new ArrayList<>();
        char[] chars = mWord.toCharArray();
        for (int i = 0; i < mWord.length(); i++) {
            Letters.add(chars[i]);
        }
        if(mLevel > 0)
            Collections.shuffle(Letters);
        return Letters;
    }
}
