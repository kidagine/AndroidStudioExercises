package com.example.tapbpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView beatsPerMinuteText;
    private long previousClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beatsPerMinuteText = findViewById(R.id.txvBeatsPerMinute);
        Button tapButton = findViewById(R.id.btnTap);
        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBeat();
            }
        });
    }

    private void tapBeat(){
        long temp = System.currentTimeMillis();
        if (previousClickTime != 0) {
            beatsPerMinuteText.setText("Beats per minute: " + (60000 / (temp - previousClickTime)));
        } else {
            beatsPerMinuteText.setText("First Click");
        }
        previousClickTime = temp;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString("beatsText", beatsPerMinuteText.getText().toString());
        outState.putLong("previousClickTime", previousClickTime);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        beatsPerMinuteText.setText(savedInstanceState.getString("beatsText"));
        previousClickTime = savedInstanceState.getLong("previousClickTime");
    }
}
