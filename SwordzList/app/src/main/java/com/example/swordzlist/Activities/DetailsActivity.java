package com.example.swordzlist.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.swordzlist.Entities.Sword;
import com.example.swordzlist.R;

public class DetailsActivity extends AppCompatActivity {

    EditText dtxName;
    EditText dtxPrice;
    Button btnSave;
    Sword swordItem;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_detail);

        dtxName = findViewById(R.id.etName);
        dtxPrice = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);
        setSwordInformation();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void setSwordInformation(){
        swordItem = (Sword) getIntent().getSerializableExtra("sword");

        dtxName.setText(swordItem.getName());
        dtxPrice.setText(String.valueOf(swordItem.getPrice()));
    }

    private void save(){
        Intent detailsIntent = new Intent(this, MainActivity.class);
        swordItem.setName(dtxName.getText().toString());
        swordItem.setPrice(Double.parseDouble(dtxPrice.getText().toString()));
        detailsIntent.putExtra("sword", swordItem);
        startActivity(detailsIntent);
    }
}
