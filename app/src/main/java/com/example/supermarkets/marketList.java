package com.example.supermarkets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;

public class marketList extends AppCompatActivity {
    RecyclerView marketList;
    SupermarketAdapter supermarketAdapter;

    ArrayList<Supermarket> supermarket;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int supermarketId = supermarket.get(position).getSupermarketID();
            Intent intent = new Intent(marketList.this, MainActivity.class);
            intent.putExtra("supermarketId", supermarketId);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_list);


        initAddSupermarketButton();
        initDeleteSwitch();
    }

    @Override
    public void onResume() {
        super.onResume();

        String sortBy = getSharedPreferences("MySupermarketListPreferences", Context.MODE_PRIVATE).getString("sortfield", "supermarketname");
        String sortOrder = getSharedPreferences("MySupermarketListPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");

       MarketDataSource ds = new MarketDataSource(this);
        try {
            ds.open();
            supermarket = ds.getSupermarkets(sortBy, sortOrder);
            ds.close();
            if (supermarket.size() > 0) {
                marketList = findViewById(R.id.rvSupermarkets);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                marketList.setLayoutManager(layoutManager);
                supermarketAdapter = new SupermarketAdapter(supermarket, this);
                supermarketAdapter.setOnItemClickListener(onItemClickListener);
                marketList.setAdapter(supermarketAdapter);
            }
            else {
                Intent intent = new Intent(marketList.this, MainActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }

    }
    private void initAddSupermarketButton() {
        Button newSupermarket = findViewById(R.id.AddSupermarketButton);
        newSupermarket.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(marketList.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDeleteSwitch() {
        Switch s = findViewById(R.id.DeleteSwitch);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean status = compoundButton.isChecked();
                supermarketAdapter.setDelete(status);
                supermarketAdapter.notifyDataSetChanged();
            }
        });
    }
}