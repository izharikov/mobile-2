package com.example.igor.lab;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {
    private TextView infoTextView;
    private Button button;
    private EditText inputNumberEditText;
    private boolean gameFinished;
    private int guess;
    private int countOfTrying = 0;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        infoTextView = (TextView) findViewById(R.id.info_text);
        button = (Button) findViewById(R.id.try_button);
        inputNumberEditText = (EditText) findViewById(R.id.input_number);
        guess = (int) (Math.random() * 100);
        gameFinished = false;
        mp = MediaPlayer.create(this, R.raw.win_music);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameFinished) {
                    String inp = inputNumberEditText.getText().toString();
                    if ( inp != null && !inp.equals("")) {
                        int inputNumber = Integer.parseInt(inp);
                        if (inputNumber > guess) {
                            infoTextView.setText(R.string.more);
                            countOfTrying++;
                        } else if (inputNumber < guess) {
                            infoTextView.setText(R.string.less);
                            countOfTrying++;
                        } else {
                            mp.start();
                            infoTextView.setText(String.format(getResources().getString(R.string.equal), countOfTrying));
                            gameFinished = true;
                            button.setText(R.string.replay);
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.warning), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    guess = (int) (Math.random() * 100);
                    gameFinished = false;
                    infoTextView.setText(R.string.hello_message);
                    button.setText(R.string.text_button);
                    countOfTrying = 0;
                }
                inputNumberEditText.setText("");
            }
        });
    }


}

// Mobile13-2016*
