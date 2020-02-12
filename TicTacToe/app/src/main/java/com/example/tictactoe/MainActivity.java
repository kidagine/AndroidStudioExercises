package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button[] btnMarks;
    private TextView txvPlayerOne, txvPlayerTwo, txvCurrentPlayer;
    private boolean isPlayerOneMove = true;
    private boolean isGameOver;
    private int playerOneScore, playerTwoScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayoutElements();
        setUpButtons();
    }

    private void initializeLayoutElements(){
        txvPlayerOne = findViewById(R.id.txvPlayerOne);
        txvPlayerTwo = findViewById(R.id.txvPlayerTwo);
        txvCurrentPlayer = findViewById(R.id.txvCurrentPlayer);
    }

    private void setUpButtons(){
        Button btn00 = findViewById(R.id.btn00);
        Button btn01 = findViewById(R.id.btn01);
        Button btn02 = findViewById(R.id.btn02);
        Button btn10 = findViewById(R.id.btn10);
        Button btn11 = findViewById(R.id.btn11);
        Button btn12 = findViewById(R.id.btn12);
        Button btn20 = findViewById(R.id.btn20);
        Button btn21 = findViewById(R.id.btn21);
        Button btn22 = findViewById(R.id.btn22);
        btnMarks = new Button[] { btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22 };
        for (Button numberButton : btnMarks) {
            numberButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setResultText((Button) v);
                }
            });
        }
    }

    private void setResultText(Button numberButton){
        if (!isGameOver){
            if (numberButton.getText().toString().isEmpty()){
                if (isPlayerOneMove){
                    numberButton.setText("X");
                    txvCurrentPlayer.setText("O");
                }
                else{
                    numberButton.setText("O");
                    txvCurrentPlayer.setText("X");
                }
                checkWin();
                isPlayerOneMove = !isPlayerOneMove;
            }
        }
        else{
            resetBoard();
        }
    }

    private void checkWin(){
        checkSpecificMarkPlacements(0, 1, 2);
        checkSpecificMarkPlacements(3, 4, 5);
        checkSpecificMarkPlacements(6, 7, 8);

        checkSpecificMarkPlacements(0, 3, 6);
        checkSpecificMarkPlacements(1, 4, 7);
        checkSpecificMarkPlacements(2, 5, 8);

        checkSpecificMarkPlacements(0, 4, 8);
        checkSpecificMarkPlacements(2, 4, 6);

        if (!isGameOver)
        checkTie();
    }

    private void checkSpecificMarkPlacements(int firstMark, int secondMark, int thirdMark){
        if (!btnMarks[firstMark].getText().toString().isEmpty()){
            if (btnMarks[firstMark].getText().toString().equals(btnMarks[secondMark].getText().toString())
                    && btnMarks[firstMark].getText().toString().equals(btnMarks[thirdMark].getText().toString())){
                GameOver();
            }
        }
    }

    private void checkTie() {
        for (int i = 0; i < btnMarks.length; i++) {
            if (btnMarks[i].getText().toString().isEmpty()){
                return;
            }
        }
        txvCurrentPlayer.setText("-");
        isGameOver = true;
    }

    private void GameOver(){
        if (isPlayerOneMove){
            playerOneScore++;
            txvPlayerOne.setText(String.valueOf(playerOneScore));
        }
        else{
            playerTwoScore++;
            txvPlayerTwo.setText(String.valueOf(playerTwoScore));
        }
        isGameOver = true;
    }

    private void resetBoard(){
        for (int i = 0; i < btnMarks.length; i++){
            btnMarks[i].setText("");
        }
        isGameOver = false;
    }
}