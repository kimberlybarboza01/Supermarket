package com.example.supermarkets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class MainActivity extends AppCompatActivity {
    private Supermarket currentSupermarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRateButton ();
        currentSupermarket = new Supermarket();
    }

    public void initRateButton (){
        Button rateBtn = findViewById(R.id.rateButton);
        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful = false;
                MarketDataSource ds = new MarketDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentSupermarket.getSupermarketID() ==-1){
                        wasSuccessful = ds.insertSupermarket(currentSupermarket);
                    }
                    if (wasSuccessful) {
                        int newId = ds.getLastMarketID();
                        currentSupermarket.setSupermarketID(newId);
                    }
                    else{
                        wasSuccessful = ds.updateSupermarket(currentSupermarket);
                    }
                    ds.close();
                }
                catch (Exception e){
                    wasSuccessful = false;
                }

                Intent intent = new Intent(MainActivity.this, Rate_Supermarket.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initTextChangedEvents (){
        final EditText etMarketName = findViewById(R.id.editName);
        etMarketName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSupermarket.setSupermarketName(etMarketName.getText().toString());

            }
        });
        final EditText etStreetAddress = findViewById(R.id.editTextStreetAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSupermarket.setStreetAddress(etStreetAddress.getText().toString());

            }
        });
        final EditText etCity = findViewById(R.id.editTextCity);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSupermarket.setCity(etCity.getText().toString());

            }
        });
        final EditText etState = findViewById(R.id.editTextState);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSupermarket.setState(etState.getText().toString());

            }
        });
        final EditText etZipcode = findViewById(R.id.editTextZipcode);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSupermarket.setZipCode(etZipcode.getText().toString());

            }
        });

    }

}