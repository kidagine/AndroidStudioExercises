package com.example.swordzlist.Activities;

import android.content.Intent;
import android.net.Uri;
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
    EditText dtxEmail;
    Button btnSave;
    Button btnEmail;
    Sword swordItem;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_detail);

        dtxName = findViewById(R.id.etName);
        dtxPrice = findViewById(R.id.etPhone);
        dtxEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        btnEmail = findViewById(R.id.btnEmail);
        setSwordInformation();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void setSwordInformation(){
        swordItem = (Sword) getIntent().getSerializableExtra("sword");

        dtxName.setText(swordItem.getName());
        dtxPrice.setText(String.valueOf(swordItem.getPrice()));
    }

    private void save(){
        swordItem.setName(dtxName.getText().toString());
        swordItem.setPrice(Double.parseDouble(dtxPrice.getText().toString()));
        Intent detailsIntent = new Intent();
        detailsIntent.putExtra("sword", swordItem);
        setResult(RESULT_OK, detailsIntent);
        finish();
    }

    private void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",dtxName.getText().toString() + "@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, dtxEmail.getText().toString());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
