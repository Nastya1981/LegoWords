package com.example.android.legowords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public int level = 0;
    ImageView imageView;
    Map<String, Integer> wordAndDrawables = new HashMap<>();

    String[] images = {
            "cat",
            "fox",
            "duck",
            "squirell"
    };
    String[] words = {
            "КОТ",
            "ЛИСА",
            "УТКА",
            "БЕЛКА"
    };
    private LinearLayout mLinearLayout;
    private TextView mTxt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplash();

//
//        setContentView(R.layout.activity_main);
//        imageView = (ImageView) findViewById(R.id.image_word);
//        mLinearLayout = (LinearLayout) findViewById(R.id.layoutChars);
//        mTxt1 = (TextView) findViewById(R.id.txt1);
//        fillWordsMap();
////        buildWord();
    }

    private void showSplash() {

    }

    private void fillWordsMap() {
        String[] strings = getResources().getStringArray(R.array.words);
        for (String string: strings){
            Log.e("Words",string);
        }
    }

    public void buildWord() {
        imageView.setImageResource(this
                .getResources()
                .getIdentifier("fox", "drawable", this
                        .getPackageName()));


        String word = words[level];
        final int word_len = word.length();
        Button[] buttons = new Button[word.length()];
        final char[] chars = word.toCharArray();
        LayoutInflater inflater = getLayoutInflater();


        for (int i = 0; i < word_len; i++) {
            buttons[i] = (Button) inflater.inflate(R.layout.btn_lay, null);
            buttons[i].setTextSize(65);
            buttons[i].setWidth(100);
            buttons[i].setHeight(100);
            buttons[i].setText(Character.toString(chars[i]));
        }


        for (Button btn : buttons) {
            // buttons[i].setId(i);
            mLinearLayout.addView(btn);
        }
        buttons[0].getText();


        final View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String letter = Character.toString(chars[mTxt1.length()]);
                Button button = (Button) v;
                String s = button.getText().toString();
                if (letter.equals(s)) {
                    mTxt1.setText(mTxt1.getText().toString() + button.getText().toString());
                    if (mTxt1.length() == word_len) {
                        if (level != 3) {
                            level++;
                            mLinearLayout.removeView(button);
                            mTxt1.setText("");
                            buildWord();
                        }
                    }
                    mLinearLayout.removeView(button);
                }
            }
        };
        for (int i = 0; i < word.length(); i++) {
            buttons[i].setOnClickListener(oclBtnOk);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
