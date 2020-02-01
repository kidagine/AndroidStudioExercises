package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private enum CalculationMode { PLUS, MINUS, MULTIPLY, DIVIDE }
    private TextView resultTextView;

    private String displayResult = "0";
    private double storedResult = 0;
    private DecimalFormat decimalFormat = new DecimalFormat("0.#");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.txvResult);

        setUpButtons();
        setUpToggles();
    }


    private void setResultText(Button numberButton){
        if (displayResult.equals("0")){
            displayResult = numberButton.getText().toString();
        }
        else{
            displayResult += numberButton.getText().toString();
        }
        resultTextView.setText(displayResult);
    }

    private void cancelResult() {
        storedResult = 0;
        displayResult = "0";
        resultTextView.setText(displayResult);
    }

    private void setNegation() {
        if (!displayResult.isEmpty()){
            resultTextView = findViewById(R.id.txvResult);
            double numberResult = Double.parseDouble(resultTextView.getText().toString());
            double displayResultNegated = numberResult * -1;
            displayResult = String.valueOf(displayResultNegated);
            resultTextView.setText(decimalFormat.format(displayResultNegated));
        }
    }

    private void setPercentage() {
        if (!displayResult.isEmpty()){
            double displayResultPercentage = Math.round(Double.parseDouble(displayResult) * 100);
            displayResult = "0";
            resultTextView.setText(displayResultPercentage + "%");
        }
    }

    private void setCalculationMode(CalculationMode calculationMode) {
        switch(calculationMode){
            case PLUS:
                storedResult += Double.parseDouble(displayResult);
                break;
            case MINUS:
                storedResult -= Double.parseDouble(displayResult);
                break;
            case MULTIPLY:
                storedResult *= Double.parseDouble(displayResult);
                break;
            case DIVIDE:
                storedResult /= Double.parseDouble(displayResult);
                break;
        }
        displayResult = "0";
        resultTextView.setText(decimalFormat.format(storedResult));
    }

    private void getEqualsResult(){
        double numberDisplay = Double.parseDouble(resultTextView.getText().toString());
        double equalsResult = numberDisplay + storedResult;
        resultTextView.setText(decimalFormat.format(equalsResult));
        displayResult = "0";
        storedResult = 0;
    }

    private void setUpButtons(){
        setUpNumberButtons();
        setUpMiscButtons();
    }

    private void setUpNumberButtons(){
        Button zeroButton = findViewById(R.id.btnZero);
        Button oneButton = findViewById(R.id.btnOne);
        Button twoButton = findViewById(R.id.btnTwo);
        Button threeButton = findViewById(R.id.btnThree);
        Button fourButton = findViewById(R.id.btnFour);
        Button fiveButton = findViewById(R.id.btnFive);
        Button sixButton = findViewById(R.id.btnSix);
        Button sevenButton = findViewById(R.id.btnSeven);
        Button eightButton = findViewById(R.id.btnEight);
        Button nineButton = findViewById(R.id.btnNine);
        Button dotButton = findViewById(R.id.btnDot);
        Button[] numberButtons = { zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, dotButton };
        for (Button numberButton : numberButtons) {
            numberButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResultText((Button) v);
                }
            });
        }
    }

    private void setUpMiscButtons(){
        Button cancelButton = findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelResult();
            }
        });
        Button negationButton = findViewById(R.id.btnNegation);
        negationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNegation();
            }
        });
        Button percentButton = findViewById(R.id.btnPercent);
        percentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPercentage();
            }
        });
    }

    private void setUpToggles(){
        ToggleButton plusToggle = findViewById(R.id.tglPlus);
        plusToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalculationMode(CalculationMode.PLUS);
            }
        });
        ToggleButton minusToggle = findViewById(R.id.tglMinus);
        minusToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalculationMode(CalculationMode.MINUS);
            }
        });
        ToggleButton multiplyToggle = findViewById(R.id.tglMultiply);
        multiplyToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalculationMode(CalculationMode.MULTIPLY);
            }
        });
        ToggleButton divideToggle = findViewById(R.id.tglDivide);
        divideToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalculationMode(CalculationMode.DIVIDE);
            }
        });
        ToggleButton equalsToggle = findViewById(R.id.tglEqual);
        equalsToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEqualsResult();
            }
        });
    }
}
