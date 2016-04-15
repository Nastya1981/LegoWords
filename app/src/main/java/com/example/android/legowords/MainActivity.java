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
    ImageView imageView;
    public int level = 0;
    int countLetter = 0;
    String printWord = "";
    String[] images = {
            "cat",
            "fox",
            "duck",
            "squirrel"
    };
    String[] words = {
            "КОТ",
            "ЛИСА",
            "УТКА",
            "БЕЛКА"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.image_word);
        buildWord();
    }
    public void buildWord(){
        countLetter = 0;
        imageView.setImageResource(this
                .getResources()
                .getIdentifier(images[level], "drawable", this
                        .getPackageName()));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutChars);
        final LinearLayout linearLayoutTextView = (LinearLayout) findViewById(R.id.layout_textview);
        String word = words[level];
        final int word_len = word.length();
        /*go to the next level*/
        final Button buttonOk = new Button(this);
        buttonOk.setWidth(100);
        buttonOk.setHeight(100);
        buttonOk.setBackgroundResource(this
                .getResources()
                .getIdentifier("ok", "drawable", this
                        .getPackageName()));
        Button[] buttons = new Button[word.length()];
        final TextView[] textViews = new TextView[word.length()];
        List<Character> solution = new ArrayList<>();
        final char[] chars = word.toCharArray();
        for (int i = 0; i < word_len; i++) {

            buttons[i] = new Button(this);
            buttons[i].setTextSize(65);
            buttons[i].setWidth(100);
            buttons[i].setHeight(100);
            textViews[i] = new TextView(this);
            textViews[i].setBackgroundResource(this
                    .getResources()
                    .getIdentifier("roundrect", "drawable", this
                            .getPackageName()));
            textViews[i].setTextSize(55);
            textViews[i].setWidth(100);
            textViews[i].setHeight(100);
        }
        for (int i = 0; i < word_len; i++) {
            solution.add(chars[i]);
            //buttons[i].setText(Character.toString(chars[i]));
          //  linearLayout.addView(buttons[i]);
        }
        if(level > 0)
            Collections.shuffle(solution);
        for (int i = 0; i < word_len; i++) {
            buttons[i].setText(Character.toString(solution.get(i)));
            linearLayout.addView(buttons[i]);
            linearLayoutTextView.addView(textViews[i]);
        }
        buttons[0].getText();

        final TextView txt1 = (TextView) findViewById(R.id.txt1);


        final View.OnClickListener oclBtnLetter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String letter = Character.toString(chars[txt1.length()]);
                Button button = (Button) v;
                String buttonLetter = button.getText().toString();
                if (letter.equals(buttonLetter)) {
                    textViews[countLetter].setText(button.getText().toString());
                    countLetter++;
                    txt1.setText(txt1.getText().toString() + button.getText().toString());
                    printWord += button.getText().toString();
                    if (txt1.length() == word_len){
                        if(level != 3) {
                            level++;
                            linearLayout.removeView(button);
                            txt1.setText("");

                            printWord= "";
                            linearLayoutTextView.addView(buttonOk);
                        }
                    }
                    linearLayout.removeView(button);
                }

            }
        };
        final View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutTextView.removeAllViews();
                buildWord();
            }
        };
        buttonOk.setOnClickListener(oclBtnOk);
        for (int i = 0; i < word.length(); i++) {
            buttons[i].setOnClickListener(oclBtnLetter);
        }
    }
}
