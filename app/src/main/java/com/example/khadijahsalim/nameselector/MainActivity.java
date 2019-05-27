package com.example.khadijahsalim.nameselector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTimeTextView, mNameTextView;
    private Button mStartButton;

    private static final int START_TIJME_IN_MILLISECONDS = 60000; // 1 minute

    private long mTimeLeftInMilliseconds = START_TIJME_IN_MILLISECONDS;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    ArrayList<String> mNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTextView = findViewById(R.id.time_text_view);
        mNameTextView = findViewById(R.id.name_text_view);

        mStartButton = findViewById(R.id.start_button);

        mNames = new ArrayList<>();
        mNames.add("name 1");
        mNames.add("name 2");
        mNames.add("name 3");
        mNames.add("name 4");
        mNames.add("name 5");
        mNames.add("name 6");
        mNames.add("name 7");
        mNames.add("name 8");
        mNames.add("name 9");

        ArrayAdapter nameAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,mNames);
        ListView listview = findViewById(R.id.names_list_view);
        listview.setAdapter(nameAdapter);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) { //if timer is already running then stop it
                    pauseTimer();
                } else { // if timer is not running then start it
                    startTimer();
                }
            }
        });
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mStartButton.setText("Start");
        // mButtonReset.setVisibility(View.VISIBLE);
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMilliseconds = millisUntilFinished;
                updateCountDownText();
                updateNameTextView();


            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mStartButton.setText("Start");
                mStartButton.setVisibility(View.INVISIBLE);
                //mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();

        mTimerRunning = true;
        mStartButton.setText("pause");
        //  mButtonReset.setVisibility(View.INVISIBLE);
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (mTimeLeftInMilliseconds / 1000) % 60;

        String name = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTimeTextView.setText(name);
    }

    private void updateNameTextView()
    {
        Random rand = new Random();
        int randomNameIndex = rand.nextInt(mNames.size());

        //update the text view with thw new random name
        mNameTextView.setText(mNames.get(randomNameIndex));
    }

}
