package com.example.android.legowords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
        imageView.setImageResource(this
                .getResources()
                .getIdentifier(images[level], "drawable", this
                        .getPackageName()));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutChars);
        String word = words[level];
        final int word_len = word.length();
        Button[] buttons = new Button[word.length()];
        List<Character> solution = new ArrayList<>();
        final char[] chars = word.toCharArray();
        for (int i = 0; i < word_len; i++) {

            buttons[i] = new Button(this);
            buttons[i].setTextSize(65);
            buttons[i].setWidth(100);
            buttons[i].setHeight(100);
        }
        for (int i = 0; i < word_len; i++) {
            // buttons[i].setId(i);
            solution.add(chars[i]);
            //buttons[i].setText(Character.toString(chars[i]));
          //  linearLayout.addView(buttons[i]);
        }
        Collections.shuffle(solution);
        for (int i = 0; i < word_len; i++) {
            buttons[i].setText(Character.toString(solution.get(i)));
            linearLayout.addView(buttons[i]);
        }
        buttons[0].getText();

        final TextView txt1 = (TextView) findViewById(R.id.txt1);
        final View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String letter = Character.toString(chars[txt1.length()]);
                Button button = (Button) v;
                String s = button.getText().toString();
                if (letter.equals(s)) {
                    txt1.setText(txt1.getText().toString() + button.getText().toString());
                    if (txt1.length() == word_len){
                        if(level != 3) {
                            level++;
                            linearLayout.removeView(button);
                            txt1.setText("");
                            buildWord();
                        }
                    }
                    linearLayout.removeView(button);
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
