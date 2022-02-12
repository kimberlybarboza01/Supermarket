package com.example.supermarkets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Supermarket currentSupermarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setForEditing(false);
        initTextChangedEvents();
        initRateButton();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            initSupermarket(extras.getInt("supermarketId"));
        } else {
            currentSupermarket = new Supermarket();
        }
    }


    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editTextName);
        EditText editAddress = findViewById(R.id.editTextAddress);
        EditText editCity = findViewById(R.id.editTextCity);
        EditText editState = findViewById(R.id.editTextState);
        EditText editZipCode = findViewById(R.id.editTextZipCode);
        Button buttonRate = findViewById(R.id.buttonRate);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);
        buttonRate.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        //else {
        //   ScrollView s = findViewById(R.id.scrollView);
        //   s.fullScroll(ScrollView.FOCUS_UP);


    }

    private void initTextChangedEvents() {
        final EditText etSupermarketName = findViewById(R.id.editTextName);
        etSupermarketName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentSupermarket.setSupermarketName(etSupermarketName.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etStreetAddress = findViewById(R.id.editTextAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentSupermarket.setStreetAddress(etStreetAddress.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etCity = findViewById(R.id.editTextCity);
        etCity.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                currentSupermarket.setCity(etCity.getText().toString());
            }
        });

        final EditText etState = findViewById(R.id.editTextState);
        etState.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                currentSupermarket.setState(etState.getText().toString());
            }
        });

        final EditText etZip = findViewById(R.id.editTextZipCode);
        etZip.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            public void afterTextChanged(Editable editable) {
                currentSupermarket.setZipCode(etZip.getText().toString());
            }
        });
    }

    private void initRateButton() {
        Button rateButton = findViewById(R.id.buttonRate);
        rateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                hideKeyboard();
                MarketDataSource ds = new MarketDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentSupermarket.getSupermarketID() == -1) {
                        wasSuccessful = ds.insertSupermarket(currentSupermarket);
                        if (wasSuccessful) {
                            int newId = ds.getLastSupermarketId();
                            currentSupermarket.setSupermarketID(newId);
                        }

                    } else {
                        wasSuccessful = ds.updateSupermarket(currentSupermarket);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    Intent intent = new Intent(MainActivity.this, Rate_Supermarket.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editTextName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editTextAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText et = findViewById(R.id.editTextCity);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editTextState);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editTextZipCode);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);

    }

    private void initSupermarket(int id) {

        MarketDataSource ds = new MarketDataSource(MainActivity.this);
        try {
            ds.open();
            currentSupermarket = ds.getSpecificSupermarket(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Supermarket Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editTextName);
        EditText editAddress = findViewById(R.id.editTextAddress);
        EditText editCity = findViewById(R.id.editTextCity);
        EditText editState = findViewById(R.id.editTextState);
        EditText editZipCode = findViewById(R.id.editTextZipCode);

        editName.setText(currentSupermarket.getSupermarketName());
        editAddress.setText(currentSupermarket.getStreetAddress());
        editCity.setText(currentSupermarket.getCity());
        editState.setText(currentSupermarket.getState());
        editZipCode.setText(currentSupermarket.getZipCode());

    }
}
