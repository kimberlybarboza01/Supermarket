package com.example.supermarkets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Rate_Supermarket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_supermarket);
        initDoneButton ();
        initSaveButton ();
    }
    public void initDoneButton (){
        Button doneBtn = findViewById(R.id.buttonDone);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rate_Supermarket.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void initSaveButton (){
        Button saveBtn = findViewById(R.id.buttonSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RatingBar rbLiquor = findViewById(R.id.ratingBarLiquor);
                RatingBar rbProduce = findViewById(R.id.ratingBarProduce);
                RatingBar rbMeat = findViewById(R.id.ratingBarMeat);
                RatingBar rbCheese = findViewById(R.id.ratingBarCheese);
                RatingBar rbEase = findViewById(R.id.ratingBarEase);

                float rateLiquor = rbLiquor.getRating();
                float rateProduce = rbProduce.getRating();
                float rateMeat = rbMeat.getRating();
                float rateCheese = rbCheese.getRating();
                float rateEase = rbEase.getRating();

                float total = (rateLiquor + rateProduce + rateMeat + rateCheese + rateEase) / 5;


                TextView avgTextView = findViewById(R.id.textViewAvg);

                avgTextView.setText(String.valueOf(total));
            }
        });

    }
}