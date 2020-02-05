package com.example.exchangecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private enum CurrencyType {EUR, USD, DKR, YEN, PND}
    private HashMap<CurrencyType, Double> EuroHashMap  = new HashMap<>(), DollarHashMap  = new HashMap<>(), PoundHashMap  = new HashMap<>(), KroneHashMap  = new HashMap<>(), YenHashMap = new HashMap<>();
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private EditText editTestUserInput;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayoutElements();
        initializeHashMapValues();
        setReverseButton();
        setCurrencyEditText();
        setCurrencySpinners();
    }

    private void initializeLayoutElements(){
        spinnerFromCurrency = findViewById(R.id.spnFromCurrency);
        spinnerToCurrency = findViewById(R.id.spnToCurrency);
        editTestUserInput = findViewById(R.id.edtUserCurrency);
        textViewResult = findViewById(R.id.txvResult);
    }

    private void initializeHashMapValues(){
        EuroHashMap.put(CurrencyType.EUR, 1.0);
        EuroHashMap.put(CurrencyType.USD, 1.10);
        EuroHashMap.put(CurrencyType.PND, 0.84);
        EuroHashMap.put(CurrencyType.DKR, 7.47);
        EuroHashMap.put(CurrencyType.YEN, 120.16);

        DollarHashMap.put(CurrencyType.EUR, 0.90);
        DollarHashMap.put(CurrencyType.USD, 1.0);
        DollarHashMap.put(CurrencyType.PND, 0.77);
        DollarHashMap.put(CurrencyType.DKR, 6.76);
        DollarHashMap.put(CurrencyType.YEN, 108.66);

        PoundHashMap.put(CurrencyType.EUR, 1.18);
        PoundHashMap.put(CurrencyType.USD, 1.30);
        PoundHashMap.put(CurrencyType.PND, 1.0);
        PoundHashMap.put(CurrencyType.DKR, 8.78);
        PoundHashMap.put(CurrencyType.YEN, 141.23);

        KroneHashMap.put(CurrencyType.EUR, 0.13);
        KroneHashMap.put(CurrencyType.USD, 0.15);
        KroneHashMap.put(CurrencyType.PND, 0.11);
        KroneHashMap.put(CurrencyType.DKR, 1.00);
        KroneHashMap.put(CurrencyType.YEN, 16.08);

        YenHashMap.put(CurrencyType.EUR, 0.0083);
        YenHashMap.put(CurrencyType.USD, 0.0092);
        YenHashMap.put(CurrencyType.PND, 0.0071);
        YenHashMap.put(CurrencyType.DKR, 0.062);
        YenHashMap.put(CurrencyType.YEN, 1.0);
    }

    private void setReverseButton() {
        Button reverseButton = findViewById(R.id.btnReverseCurrency);
        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverseCurrencySpinners();
            }
        });
    }

    private void reverseCurrencySpinners(){
        int temp = spinnerFromCurrency.getSelectedItemPosition();
        spinnerFromCurrency.setSelection(spinnerToCurrency.getSelectedItemPosition());
        spinnerToCurrency.setSelection(temp);
    }

    private void setCurrencySpinners(){
        String[] arraySpinner = new String[] { CurrencyType.EUR.toString(), CurrencyType.USD.toString(), CurrencyType.PND.toString(), CurrencyType.DKR.toString(), CurrencyType.YEN.toString() };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCurrency.setAdapter(adapter);
        spinnerToCurrency.setAdapter(adapter);

        spinnerFromCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setFromCurrency(CurrencyType.valueOf(spinnerFromCurrency.getSelectedItem().toString()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
        spinnerToCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setFromCurrency(CurrencyType.valueOf(spinnerFromCurrency.getSelectedItem().toString()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) { }
        });
    }

    private void setCurrencyEditText() {

        editTestUserInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setFromCurrency(CurrencyType.valueOf(spinnerFromCurrency.getSelectedItem().toString()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void setFromCurrency(CurrencyType currencyType){
        if (!editTestUserInput.getText().toString().isEmpty()) {
            if (editTestUserInput.getText().length() <= 9){
                CurrencyType toCurrencyType = CurrencyType.valueOf(spinnerToCurrency.getSelectedItem().toString());
                switch (currencyType) {
                    case EUR:
                        setToCurrency(EuroHashMap, toCurrencyType);
                        break;
                    case USD:
                        setToCurrency(DollarHashMap, toCurrencyType);
                        break;
                    case PND:
                        setToCurrency(PoundHashMap, toCurrencyType);
                        break;
                    case DKR:
                        setToCurrency(KroneHashMap, toCurrencyType);
                        break;
                    case YEN:
                        setToCurrency(YenHashMap, toCurrencyType);
                        break;
                }
            }
        }
    }

    private void setToCurrency(HashMap<CurrencyType, Double> currencyHashMap, CurrencyType currencyType){
        double currencyValue = Double.parseDouble(editTestUserInput.getText().toString());
        double convertedCurrency = 0;
        Locale country = null;
        switch(currencyType){
            case EUR:
                country = new Locale("fr", "FR");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.EUR);
                break;
            case USD:
                country = new Locale("en", "US");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.USD);
                break;
            case PND:
                country = new Locale("en", "GB");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.PND);
                break;
            case DKR:
                country = new Locale("da", "DK");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.DKR);
                break;
            case YEN:
                country = new Locale("ja", "JP");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.YEN);
                break;
        }

        NumberFormat countryFormat = NumberFormat.getCurrencyInstance(country);
        textViewResult.setText(countryFormat.format(convertedCurrency));
    }
}
