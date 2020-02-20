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

    private enum CurrencyType {EUR, USD, DKK, JPY, GBP}
    private HashMap<CurrencyType, Double> EuroHashMap  = new HashMap<>(), DollarHashMap  = new HashMap<>(), PoundHashMap  = new HashMap<>(), KroneHashMap  = new HashMap<>(), YenHashMap = new HashMap<>();
    private Spinner spinnerFromCurrency, spinnerToCurrency;
    private EditText editTextUserInput;
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
        editTextUserInput = findViewById(R.id.edtUserCurrency);
        textViewResult = findViewById(R.id.txvResult);
    }

    private void initializeHashMapValues(){
        EuroHashMap.put(CurrencyType.EUR, 1.0);
        EuroHashMap.put(CurrencyType.USD, 1.10);
        EuroHashMap.put(CurrencyType.GBP, 0.84);
        EuroHashMap.put(CurrencyType.DKK, 7.47);
        EuroHashMap.put(CurrencyType.JPY, 120.16);

        DollarHashMap.put(CurrencyType.EUR, 0.90);
        DollarHashMap.put(CurrencyType.USD, 1.0);
        DollarHashMap.put(CurrencyType.GBP, 0.77);
        DollarHashMap.put(CurrencyType.DKK, 6.76);
        DollarHashMap.put(CurrencyType.JPY, 108.66);

        PoundHashMap.put(CurrencyType.EUR, 1.18);
        PoundHashMap.put(CurrencyType.USD, 1.30);
        PoundHashMap.put(CurrencyType.GBP, 1.0);
        PoundHashMap.put(CurrencyType.DKK, 8.78);
        PoundHashMap.put(CurrencyType.JPY, 141.23);

        KroneHashMap.put(CurrencyType.EUR, 0.13);
        KroneHashMap.put(CurrencyType.USD, 0.15);
        KroneHashMap.put(CurrencyType.GBP, 0.11);
        KroneHashMap.put(CurrencyType.DKK, 1.00);
        KroneHashMap.put(CurrencyType.JPY, 16.08);

        YenHashMap.put(CurrencyType.EUR, 0.0083);
        YenHashMap.put(CurrencyType.USD, 0.0092);
        YenHashMap.put(CurrencyType.GBP, 0.0071);
        YenHashMap.put(CurrencyType.DKK, 0.062);
        YenHashMap.put(CurrencyType.JPY, 1.0);
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
        String[] arraySpinner = new String[] { CurrencyType.EUR.toString(), CurrencyType.USD.toString(), CurrencyType.GBP.toString(), CurrencyType.DKK.toString(), CurrencyType.JPY.toString() };
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

        editTextUserInput.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                setFromCurrency(CurrencyType.valueOf(spinnerFromCurrency.getSelectedItem().toString()));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void setFromCurrency(CurrencyType currencyType){
        if (!editTextUserInput.getText().toString().isEmpty()) {
            if (editTextUserInput.getText().length() <= 9){
                CurrencyType toCurrencyType = CurrencyType.valueOf(spinnerToCurrency.getSelectedItem().toString());
                switch (currencyType) {
                    case EUR:
                        setToCurrency(EuroHashMap, toCurrencyType);
                        break;
                    case USD:
                        setToCurrency(DollarHashMap, toCurrencyType);
                        break;
                    case GBP:
                        setToCurrency(PoundHashMap, toCurrencyType);
                        break;
                    case DKK:
                        setToCurrency(KroneHashMap, toCurrencyType);
                        break;
                    case JPY:
                        setToCurrency(YenHashMap, toCurrencyType);
                        break;
                }
            }
        }
    }

    private void setToCurrency(HashMap<CurrencyType, Double> currencyHashMap, CurrencyType currencyType){
        double currencyValue = Double.parseDouble(editTextUserInput.getText().toString());
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
            case GBP:
                country = new Locale("en", "GB");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.GBP);
                break;
            case DKK:
                country = new Locale("da", "DK");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.DKK);
                break;
            case JPY:
                country = new Locale("ja", "JP");
                convertedCurrency = currencyValue * currencyHashMap.get(CurrencyType.JPY);
                break;
        }

        NumberFormat countryFormat = NumberFormat.getCurrencyInstance(country);
        textViewResult.setText(countryFormat.format(convertedCurrency));
    }
}
