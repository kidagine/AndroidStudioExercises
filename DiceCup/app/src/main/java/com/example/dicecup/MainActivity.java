package com.example.dicecup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Spinner spnDiceOne, spnDiceTwo;
    private TextView txvDiceOne, txvDiceTwo;
    private ListView lsvHistory;
    private List<String> dicesHistory = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialzeLayoutElements();

        setDiceSpinners();
        setButton();
    }

    private void initialzeLayoutElements(){
        spnDiceOne = findViewById(R.id.spnDiceOne);
        spnDiceTwo = findViewById(R.id.spnDiceTwo);
        txvDiceOne = findViewById(R.id.txvDiceOne);
        txvDiceTwo = findViewById(R.id.txvDiceTwo);
        lsvHistory = findViewById(R.id.lsvHistory);
    }

    private void setDiceSpinners(){
        Integer[] arraySpinner = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDiceOne.setAdapter(adapter);
        spnDiceTwo.setAdapter(adapter);

        spnDiceOne.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txvDiceOne.setText(spnDiceOne.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        spnDiceTwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txvDiceTwo.setText(spnDiceTwo.getSelectedItem().toString());

                if (!spnDiceTwo.getSelectedItem().toString().equals("0"))
                saveToHistory((int)spnDiceOne.getSelectedItem(), (int)spnDiceTwo.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void setButton(){
        Button btnRoll = findViewById(R.id.btnRoll);
        Button btnClear = findViewById(R.id.btnClear);
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistory();
            }
        });
    }

    private void rollDice(){
        int diceOneValue = new Random().nextInt(7);
        int diceTwoValue = new Random().nextInt(7);

        txvDiceOne.setText(String.valueOf(diceOneValue));
        txvDiceTwo.setText(String.valueOf(diceTwoValue));

        saveToHistory(diceOneValue, diceTwoValue);
    }

    private void saveToHistory(int diceOneValue, int diceTwoValue) {
        String diceHistory = dicesHistory.size() + ": " + diceOneValue + " - " + diceTwoValue;

        if (dicesHistory.size() <= 5){
            dicesHistory.add(diceHistory);
        }
        else{
            dicesHistory.add(0, diceHistory);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dicesHistory);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lsvHistory.setAdapter(adapter);
    }

    private void clearHistory(){
        dicesHistory.clear();
        lsvHistory.setAdapter(null);
    }
}
